package com.rslakra.patterns.s2w.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 * An implementation of the dispatcher interface for processing
 * workflows.  A workflow is a linear path through a set of states,
 * with actions to determine each state transition.
 */
public class WorkflowDispatcher implements Dispatcher {
    /** tags used in the XML definition of workflows and states */
    private static final String WORKFLOW_TAG = "workflow";
    private static final String STATE_TAG = "state";
    
    /** attributes shared with JSP pages */
    private static final String NAME_ATTR = "name";
    private static final String ACTION_ATTR = "action";
    private static final String VIEW_ATTR = "viewURI";
    
    /** where action classes can be found */
    private static final String ACTION_PREFIX = "com.rslakra.patterns.s2w.controller.actions.";
    
    /**
     * Internal representation of a state from the XML file
     */
    class State {
        /** state name */
        protected String name;
        
        /** transition action */
        protected Action action;
        
        /** uri to display */
        protected String viewUri;
    }
    
    /** the servlet context */
    private ServletContext context;
    
    /** the states this dispatcher uses as data */
    private State[] states;
    
    /** the current state of this dispatcher */
    private int currentState;
    
    /**
     * Called by the container to set the servlet context when this
     * dispatcher is initialized.
     */
    public void setContext(ServletContext context) throws IOException {
        // store the context
        this.context = context;
        
        // load the workflow from this file & parse it
        InputStream is = context.getResourceAsStream("/LanguageWorkflow.xml");
        try {
            states = parseXML(is);
        } catch(Exception ex) {
            throw new IOException(ex.getMessage());
        }
        
        // initialize the current state
        currentState = 0;
    }
    
    /**
     * Get the next page to forward the user to
     */
    public String getNextPage(HttpServletRequest req) {
        State s = states[currentState];
        
        // increment the state only if the action suceeds
        if ((s.action == null) || s.action.performAction(req, context)) {
            if (currentState < states.length - 1) {
                s = states[++currentState];
            } else {
                currentState = 0;
                s = states[currentState];
            }
        }
        
        // return the uri of the current state
        return s.viewUri;
    }
    
    /**
     * Parse the states XML file
     */
    private State[] parseXML(InputStream is) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document doc = builder.parse(is);
       
        // find the workflow element
        NodeList workflows = doc.getElementsByTagName(WORKFLOW_TAG);
        Element workflow = (Element)workflows.item(0);
        
        // find all the states
        NodeList states = doc.getElementsByTagName(STATE_TAG);
        State[] stateList = new State[states.getLength()];
        
        // read state information
        for(int i = 0; i < states.getLength(); i++) {
            stateList[i] = new State();
            
            Element curState = (Element)states.item(i);
            stateList[i].name = curState.getAttribute(NAME_ATTR);
            stateList[i].viewUri = curState.getAttribute(VIEW_ATTR);
            
            // convert action names into class instances
            String action = curState.getAttribute(ACTION_ATTR);
            if (action != null && action.length() > 0) {
                Class c = Class.forName(ACTION_PREFIX + action);
                stateList[i].action = (Action)c.newInstance();
            }
        }
        
        return stateList;
    }
}

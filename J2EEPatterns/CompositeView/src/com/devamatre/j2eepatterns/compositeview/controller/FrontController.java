package com.devamatre.j2eepatterns.compositeview.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A front controller that uses dispatchers to determine what pages to forward
 * to.
 */
public class FrontController extends HttpServlet {
	/** the session attribute for the current dispatcher */
	private static final String DISPATCHER_ATTR = "Dispatcher";
	
	/** the class prefix for dispatcher classes */
	private static final String DISPATCHER_PREFIX = "com.devamatre.j2eepatterns.compositeview.controller.";
	
	/**
	 * Handle GET requests by forwarding to a common method
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * Handle POST requests by forwarding to a common method
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * Process a request by forwarding to the appropriate dispatcher
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context = getServletContext();
		
		// get the last element of the request in lower case
		String reqPath = request.getPathInfo();
		reqPath = Character.toUpperCase(reqPath.charAt(1)) + reqPath.substring(2).toLowerCase();
		
		// find the dispatcher in the session
		Dispatcher dispatcher = (Dispatcher) session.getAttribute(reqPath + DISPATCHER_ATTR);
		
		// if no dispatcher was found, create one
		if (dispatcher == null) {
			String className = reqPath + "Dispatcher";
			try {
				Class c = Class.forName(DISPATCHER_PREFIX + className);
				dispatcher = (Dispatcher) c.newInstance();
			} catch (Exception ex) {
				throw new ServletException("Can't find class " + className, ex);
			}
			
			dispatcher.setContext(context);
			session.setAttribute(reqPath + DISPATCHER_ATTR, dispatcher);
		}
		
		// make sure we don't cache dynamic data
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		// use the dispatcher to find the next page
		String nextPage = dispatcher.getNextPage(request);
		
		// forward control to the view
		RequestDispatcher forwarder = request.getRequestDispatcher("/" + nextPage);
		forwarder.forward(request, response);
	}
}

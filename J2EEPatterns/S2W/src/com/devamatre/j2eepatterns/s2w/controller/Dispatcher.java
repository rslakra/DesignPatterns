package com.devamatre.patterns.s2w.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * The interface that dispatchers implement
 */
public interface Dispatcher {
    /**
     * Called by the container to set the servlet context
     * before the dispatcher is used.
     */
    public void setContext(ServletContext context) throws IOException;
    
    /**
     * Called when a request is routed to this dispatcher.
     *
     * @return the URL of the next page to go to.
     */
    public String getNextPage(HttpServletRequest req);
}

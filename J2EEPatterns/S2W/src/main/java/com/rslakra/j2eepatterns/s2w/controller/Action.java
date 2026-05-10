package com.rslakra.patterns.s2w.controller;

import javax.servlet.http.*;
import javax.servlet.*;

/**
 * The interface that all actions implement
 */
public interface Action {
    /**
     * Perform the action in question.
     *
     * @return true if the action suceeded and we should move to the
     *         next state, or false if not
     */
    public boolean performAction(HttpServletRequest req,
                                 ServletContext context);
}

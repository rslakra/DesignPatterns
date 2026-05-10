package com.rslakra.patterns.s2w.controller.actions;

import com.rslakra.patterns.s2w.controller.*;
import com.rslakra.patterns.s2w.model.*;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 * An action to reset to the start of the WorkFlow
 */
public class RestartAction implements Action {
    /**
     * Perform this action.  Invalidate the session and create a new 
     * UserBean to replace the old one.
     */
    public boolean performAction(HttpServletRequest req,
                                 ServletContext context)
    {
        // invalidate the current session
        req.getSession().invalidate();
     
        // create a new one, and add a new UserBean to it
        HttpSession session = req.getSession();
        UserBean ub = UserBeanFactory.newInstance();
        session.setAttribute(LoginAction.USERBEAN_ATTR, ub);
        
        return true;
    }
}

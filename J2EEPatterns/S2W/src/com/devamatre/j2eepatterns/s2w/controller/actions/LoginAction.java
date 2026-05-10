package com.devamatre.patterns.s2w.controller.actions;

import com.devamatre.patterns.s2w.controller.*;
import com.devamatre.patterns.s2w.model.*;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 * An action to log a user in
 */
public class LoginAction implements Action {
    /** The name of the UserBean in the session */
    public static final String USERBEAN_ATTR = "userbean";
    
    /** parameters shared with the JSP page */
    private static final String NAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";
    
    /**
     * Perform this action.  Try to log the user in based on the username
     * and password in the submitted request
     */
    public boolean performAction(HttpServletRequest req, 
                                 ServletContext context)
    {
        // read the username and password from the request
        String username = req.getParameter(NAME_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        
        // get the UserBean from the session
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute(USERBEAN_ATTR);
        
        // if the UserBean doesn't exist, create it
        if (ub == null) {
            ub = UserBeanFactory.newInstance();
            session.setAttribute(USERBEAN_ATTR, ub); 
        }
        
        // set the parameters in the bean
        ub.setUsername(username);
        ub.setPassword(password);
        
        // try to login
        return ub.doLogin();
    } 
}

package com.devamatre.patterns.s2w.controller.actions;

import com.devamatre.patterns.s2w.controller.*;
import com.devamatre.patterns.s2w.model.*;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 * An action to set the language based on a form
 */
public class LanguageAction implements Action {
    /** attribute name for the language element in the JSP page */
    private static final String LANGUAGE_PARAM = "language";
    
    /**
     * Perform this action.  Set the language property in the UserBean
     */
    public boolean performAction(HttpServletRequest req,
                                 ServletContext context)
    {
        // read the parameter from the request
        String language = req.getParameter(LANGUAGE_PARAM);
        
        // set it in the UserBean
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute(LoginAction.USERBEAN_ATTR);
        ub.setLanguage(language);
        
        // we suceeded
        return true;
    }  
}

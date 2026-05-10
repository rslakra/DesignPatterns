package com.devamatre.j2eepatterns.frontcontroller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devamatre.j2eepatterns.frontcontroller.model.UserBean;
import com.devamatre.j2eepatterns.frontcontroller.model.UserBeanFactory;

/**
 * A simple implementation of a front controller servlet. This servlet
 * intercepts all requests to urls starting with "/pages." After processing
 * it strips "/pages" off the given URL and forwards to that.
 */
public class FrontController extends HttpServlet {
	// parameters shared with login.jsp. These must exactly match the
	// property names in the JSP.
	public static final String USERNAME_PARAM = "username";
	public static final String PASSWORD_PARAM = "password";
	public static final String USERBEAN_ATTR = "userbean";
	public static final String CONTROLLER_PREFIX = "/pages";
	
	/**
	 * Handle get requests by forwarding to a common processor
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * Handle post requests by forwarding to a common processor
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * The common processor method. Check if this user is logged in or not.
	 * If they are not, forward to the login page, otherwise forward to the
	 * requested URL.
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// the default next page
		String nextPage = request.getPathInfo();
		
		// find userbean from session
		HttpSession session = request.getSession(true);
		UserBean userBean = (UserBean) session.getAttribute(USERBEAN_ATTR);
		
		if (userBean == null || !userBean.isLoggedIn()) {
			// read request parameters
			String username = request.getParameter(USERNAME_PARAM);
			String password = request.getParameter(PASSWORD_PARAM);
			
			// if the userbean doesn't exists, create it
			if (userBean == null) {
				userBean = UserBeanFactory.newInstance();
				session.setAttribute(USERBEAN_ATTR, userBean);
			}
			
			// record the username and password values
			userBean.setUsername(username);
			userBean.setPassword(password);
			
			// attempt to login
			boolean result = userBean.doLogin();
			
			// if we failed, redirect to the login page
			if (!result) {
				nextPage = "/login.jsp";
			}
		}
		
		// transfer control to the desired view
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
}

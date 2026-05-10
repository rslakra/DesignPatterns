package com.devamatre.j2eepatterns.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devamatre.j2eepatterns.mvc.model.MailingBean;
import com.devamatre.j2eepatterns.mvc.model.MailingBeanFactory;

/**
 * A simple implementation of a servlet-based controller, using the
 * MailingBean model from the model package.
 */
public class ListController extends HttpServlet {
	// parameters shared with the JSP. Note these values must be the same
	// as the property names in the JSP files.
	public static final String FIRST_PARAM = "first";
	public static final String LAST_PARAM = "last";
	public static final String EMAIL_PARAM = "email";
	public static final String MAILINGBEAN_ATTR = "mailingbean";
	
	/**
	 * Respond to an HTTP GET request.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// read the paremeters from the request
		String first = request.getParameter(FIRST_PARAM);
		String last = request.getParameter(LAST_PARAM);
		String email = request.getParameter(EMAIL_PARAM);
		
		// get the mailing list bean for this list
		MailingBean mb = MailingBeanFactory.newInstance();
		
		// set the parameters into the bean
		mb.setFirst(first);
		mb.setLast(last);
		mb.setEmail(email);
		
		// store a copy of the bean in the request context
		request.setAttribute(MAILINGBEAN_ATTR, mb);
		
		// perform the business method
		boolean result = mb.doSubscribe();
		
		// choose a page based on the result
		String nextPage = "/success.jsp";
		if (!result)
			nextPage = "/failure.jsp";
		
		// transfer control to the selected view
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
}

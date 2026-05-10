package com.devamatre.j2eepatterns.antipatterns;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeakyServlet extends HttpServlet {
	private class AddressBean {
		void setFirst(String first) {
		}
		
		void setLast(String last) {
		}
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		AddressBean address = new AddressBean();
		address.setFirst(request.getParameter("first"));
		address.setLast(request.getParameter("last"));
		
		session.setAttribute("antipatterns.address", address);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddressView.jsp");
		dispatcher.forward(request, response);
	}
}

package com.rslakra.j2eepatterns.antipatterns.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rslakra.j2eepatterns.antipatterns.model.AddressBookCommand;
import com.rslakra.j2eepatterns.antipatterns.model.CreateAddressCommand;
import com.rslakra.j2eepatterns.antipatterns.model.CreatePersonCommand;
import com.rslakra.j2eepatterns.antipatterns.model.DemoPersonCommand;
import com.rslakra.j2eepatterns.antipatterns.model.EJBFacadePersonCommand;
import com.rslakra.j2eepatterns.antipatterns.model.EJBPersonCommand;
import com.rslakra.j2eepatterns.antipatterns.model.LdapPersonCommand;
import com.rslakra.j2eepatterns.antipatterns.model.PersonCommand;

public class PersonServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method == null)
			method = "";
		
		PersonCommand personCommand = null;
		
		if (method.equalsIgnoreCase("create")) {
			String type = request.getParameter("type");
			if (type == null)
				type = "person";
			
			getServletContext().log("Creating entries...");
			try {
				if (type.equalsIgnoreCase("address")) {
					CreateAddressCommand cc = new CreateAddressCommand();
					cc.initialize();
					cc.runCommand();
				} else {
					CreatePersonCommand cc = new CreatePersonCommand();
					cc.initialize();
					cc.runCommand();
				}
				
				getServletContext().log("Done creating entries");
			} catch (Exception ce) {
				ce.printStackTrace();
				throw new ServletException("Error in create: " + ce, ce);
			}
		} else if (method.equalsIgnoreCase("reset")) {
			request.getSession().invalidate();
		} else if (method.equalsIgnoreCase("ejb")) {
			personCommand = new EJBPersonCommand();
		} else if (method.equalsIgnoreCase("ldap")) {
			personCommand = new LdapPersonCommand();
		} else if (method.equalsIgnoreCase("addressbook")) {
			String owner = request.getParameter("owner");
			personCommand = new AddressBookCommand(owner);
		} else {
			personCommand = new EJBFacadePersonCommand();
		}
		
		if (personCommand == null) {
			return;
		}
		
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		
		try {
			personCommand.setFirstName(firstName);
			personCommand.setLastName(lastName);
			
			getServletContext().log("Class is: " + personCommand);
			
			try {
				personCommand.initialize(request.getSession());
			} catch (NamingException ne) {
				if ("ejb".equalsIgnoreCase(method) && personCommand instanceof EJBPersonCommand) {
					getServletContext().log("EJB JNDI not available; using in-memory demo data. " + ne.getMessage());
					personCommand = new DemoPersonCommand();
					personCommand.setFirstName(firstName);
					personCommand.setLastName(lastName);
					personCommand.initialize(request.getSession());
					request.setAttribute("demoDataNotice", Boolean.TRUE);
				} else {
					throw ne;
				}
			}
			
			long startTime = System.currentTimeMillis();
			getServletContext().log("Starting read at: " + startTime);
			
			personCommand.runCommand();
			
			long endTime = System.currentTimeMillis();
			getServletContext().log("Finished read at: " + endTime);
			getServletContext().log(((endTime - startTime) / 1000) + " seconds elapsed.");
			
			request.setAttribute("personCommand", personCommand);
		} catch (NamingException ne) {
			throw new ServletException("Error executing command", ne);
		}
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/PersonView.jsp");
		dispatch.forward(request, response);
	}
}

package com.devamatre.j2eepatterns.antipatterns;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MagicServlet extends HttpServlet {
	private DirContext peopleContext;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost/o=jndiTest");
		env.put(Context.SECURITY_PRINCIPAL, "cn=Manager, o=jndiTest");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		
		try {
			DirContext initalContext = new InitialDirContext(env);
			peopleContext = (DirContext) initalContext.lookup("ou=people");
		} catch (NamingException ne) {
			ne.printStackTrace();
			throw new ServletException("Error inializing LDAP", ne);
		}
		
	}
	
	public void destroy() {
		try {
			peopleContext.close();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table>");
		
		try {
			NamingEnumeration people = peopleContext.list("");
			while (people.hasMore()) {
				NameClassPair personName = (NameClassPair) people.next();
				Attributes personAttrs = peopleContext.getAttributes(personName.getName());
				Attribute cn = personAttrs.get("cn");
				Attribute sn = personAttrs.get("sn");
				Attribute phone = personAttrs.get("telephoneNumber");
				
				out.println("<tr><td>" + cn.get() + " " + sn.get() + "</td>" + "<td>" + phone.get() + "</td></tr>");
			}
		} catch (Exception ex) {
			out.println("Error " + ex + " getting data!");
		}
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
}

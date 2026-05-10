package com.rslakra.j2eepatterns.antipatterns.model;

import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.servlet.http.HttpSession;

import com.rslakra.j2eepatterns.antipatterns.ejbs.Person;

public class LdapPersonCommand implements PersonCommand {
	private DirContext peopleContext;
	
	private Vector people;
	
	private String firstName;
	private String lastName;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void initialize(HttpSession session) throws NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost/o=jndiTest");
		env.put(Context.SECURITY_PRINCIPAL, "cn=Manager, o=jndiTest");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		
		DirContext initalContext = new InitialDirContext(env);
		peopleContext = (DirContext) initalContext.lookup("ou=people");
		
		people = new Vector();
	}
	
	public void runCommand() throws NamingException {
		String filter = "(objectclass=person)";
		
		if (firstName != null && lastName != null) {
			filter = "& (cn=" + firstName + ") (sn=" + lastName + ")";
		} else if (firstName != null) {
			filter = "cn=" + firstName;
		} else if (lastName != null) {
			filter = "sn=" + lastName;
		}
		
		System.out.println("filter = " + filter);
		
		SearchControls sc = new SearchControls();
		sc.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		
		NamingEnumeration peopleEnum = peopleContext.search("", filter, sc);
		while (peopleEnum.hasMore()) {
			NameClassPair personName = (NameClassPair) peopleEnum.next();
			Attributes personAttrs = peopleContext.getAttributes(personName.getName());
			Attribute cn = personAttrs.get("cn");
			Attribute sn = personAttrs.get("sn");
			Attribute phone = personAttrs.get("telephoneNumber");
			
			people.add(new Person((String) cn.get(), (String) sn.get(), (String) phone.get()));
		}
		
		peopleContext.close();
	}
	
	public List getPeople() {
		return people;
	}
}

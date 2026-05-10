package com.rslakra.j2eepatterns.antipatterns.model;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpSession;

import com.rslakra.j2eepatterns.antipatterns.ejbs.EJBPerson;
import com.rslakra.j2eepatterns.antipatterns.ejbs.EJBPersonHome;
import com.rslakra.j2eepatterns.antipatterns.ejbs.Person;

public class EJBPersonCommand implements PersonCommand {
	private String firstName;
	private String lastName;
	
	private Vector people;
	
	private EJBPersonHome personHome;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void initialize(HttpSession session) throws NamingException {
		Hashtable properties = new Properties();
		// properties.put(Context.INITIAL_CONTEXT_FACTORY,
		// "com.sun.jndi.cosnaming.CNCtxFactory");
		// properties.put(Context.PROVIDER_URL, "iiop://wgs97-74:1050");
		
		InitialContext ic = new InitialContext(properties);
		Object personRef = ic.lookup("ejb/EJBPerson");
		
		personHome = (EJBPersonHome) PortableRemoteObject.narrow(personRef, EJBPersonHome.class);
		
		people = new Vector();
	}
	
	public void runCommand() throws NamingException {
		try {
			Collection ejbpeople = null;
			
			if (firstName != null & lastName != null) {
				ejbpeople = personHome.findByName(firstName, lastName);
			} else if (firstName != null) {
				ejbpeople = personHome.findByFirstName(firstName);
			} else if (lastName != null) {
				ejbpeople = personHome.findByLastName(lastName);
			} else {
				ejbpeople = personHome.findAll();
			}
			
			for (Iterator i = ejbpeople.iterator(); i.hasNext();) {
				EJBPerson ejbPerson = (EJBPerson) i.next();
				people.add(new Person(ejbPerson.getFirstName(), ejbPerson.getLastName(), ejbPerson.getPhoneNumber()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		
	}
	
	public List getPeople() {
		return people;
	}
}

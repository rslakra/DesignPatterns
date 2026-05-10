package com.rslakra.j2eepatterns.antipatterns.model;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpSession;

import com.rslakra.j2eepatterns.antipatterns.ejbs.PeopleDTO;
import com.rslakra.j2eepatterns.antipatterns.ejbs.PersonFacade;
import com.rslakra.j2eepatterns.antipatterns.ejbs.PersonFacadeHome;

public class EJBFacadePersonCommand implements PersonCommand {
	private String firstName;
	private String lastName;
	
	private List people;
	
	private PersonFacadeHome personFacadeHome;
	
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
		Object personRef = ic.lookup("ejb/PersonFacade");
		
		personFacadeHome = (PersonFacadeHome) PortableRemoteObject.narrow(personRef, PersonFacadeHome.class);
	}
	
	public void runCommand() throws NamingException {
		try {
			PersonFacade facade = personFacadeHome.create();
			PeopleDTO peopleDto = facade.findByName(firstName, lastName);
			
			people = peopleDto.getPeople();
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		
	}
	
	public List getPeople() {
		return people;
	}
}

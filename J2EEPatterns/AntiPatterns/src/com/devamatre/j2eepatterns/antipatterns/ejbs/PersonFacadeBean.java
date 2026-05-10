package com.devamatre.j2eepatterns.antipatterns.ejbs;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class PersonFacadeBean implements SessionBean {
	private SessionContext context;
	private LocalEJBPersonHome personHome;
	
	public void setSessionContext(SessionContext aContext) {
		context = aContext;
	}
	
	public void ejbActivate() {
	}
	
	public void ejbPassivate() {
	}
	
	public void ejbRemove() {
	}
	
	public void ejbCreate() {
		try {
			InitialContext ic = new InitialContext();
			personHome = (LocalEJBPersonHome) ic.lookup("java:comp/env/ejb/local/Person");
		} catch (Exception ex) {
			throw new EJBException("Error looking up PersonHome: " + ex, ex);
		}
	}
	
	public PeopleDTO findByName(String firstName, String lastName) throws FinderException {
		Collection c = null;
		if (firstName != null && lastName != null) {
			c = personHome.findByName(firstName, lastName);
		} else if (firstName != null) {
			c = personHome.findByFirstName(firstName);
		} else if (lastName != null) {
			c = personHome.findByLastName(lastName);
		} else {
			c = personHome.findAll();
		}
		
		return getDTOFromCollection(c);
	}
	
	private PeopleDTO getDTOFromCollection(Collection people) {
		PeopleDTO dto = new PeopleDTO();
		
		for (Iterator i = people.iterator(); i.hasNext();) {
			LocalEJBPerson ejbPerson = (LocalEJBPerson) i.next();
			
			dto.addPerson(new Person(ejbPerson.getFirstName(), ejbPerson.getLastName(), ejbPerson.getPhoneNumber()));
		}
		
		return dto;
	}
}

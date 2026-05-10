package com.devamatre.j2eepatterns.antipatterns.ejbs;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2017-09-23 11:17:57 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class AddressBookBean implements javax.ejb.SessionBean {
	private SessionContext context;
	private String userName;
	private LocalAddressBookEntryHome abeHome;
	
	public void setSessionContext(SessionContext aContext) {
		context = aContext;
	}
	
	public void ejbActivate() {
		init();
	}
	
	public void ejbPassivate() {
		abeHome = null;
	}
	
	public void ejbRemove() {
		abeHome = null;
	}
	
	public void ejbCreate(String userName) throws CreateException {
		this.userName = userName;
		init();
	}
	
	public PeopleDTO findByName(String firstName, String lastName) throws FinderException {
		Collection c = null;
		
		if (firstName != null && lastName != null) {
			c = abeHome.findByName(userName, firstName, lastName);
		} else if (firstName != null) {
			c = abeHome.findByFirstName(userName, firstName);
		} else if (lastName != null) {
			c = abeHome.findByLastName(userName, lastName);
		} else {
			c = abeHome.findAll(userName);
		}
		
		return (getDTOFromCollection(c));
	}
	
	private PeopleDTO getDTOFromCollection(Collection people) {
		PeopleDTO dto = new PeopleDTO();
		
		for (Iterator i = people.iterator(); i.hasNext();) {
			LocalAddressBookEntry entry = (LocalAddressBookEntry) i.next();
			
			dto.addPerson(new Person(entry.getFirstName(), entry.getLastName(), entry.getPhoneNumber()));
		}
		
		return dto;
	}
	
	private void init() throws EJBException {
		try {
			InitialContext ic = new InitialContext();
			abeHome = (LocalAddressBookEntryHome) ic.lookup("java:comp/env/ejb/local/AddressBookEntry");
		} catch (Exception ex) {
			throw new EJBException("Error activating", ex);
		}
	}
	
	public void addEntry(int id, String owner, String firstName, String lastName, String phoneNumber) throws CreateException {
		abeHome.create(id, owner, firstName, lastName, phoneNumber);
	}
	
}

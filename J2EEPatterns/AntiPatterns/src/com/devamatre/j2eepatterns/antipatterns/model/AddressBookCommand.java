package com.devamatre.j2eepatterns.antipatterns.model;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpSession;

import com.devamatre.j2eepatterns.antipatterns.ejbs.AddressBook;
import com.devamatre.j2eepatterns.antipatterns.ejbs.AddressBookHome;
import com.devamatre.j2eepatterns.antipatterns.ejbs.PeopleDTO;

public class AddressBookCommand implements PersonCommand {
	private static final String ADDRESSBOOK_ATTR = "addressBook";
	
	private String firstName;
	private String lastName;
	private String owner;
	
	private List people;
	
	private AddressBook addressBook;
	
	public AddressBookCommand(String owner) {
		this.owner = owner;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void initialize(HttpSession session) throws NamingException {
		addressBook = (AddressBook) session.getAttribute(owner + "-" + ADDRESSBOOK_ATTR);
		if (addressBook == null) {
			Hashtable properties = new Properties();
			// properties.put(Context.INITIAL_CONTEXT_FACTORY,
			// "com.sun.jndi.cosnaming.CNCtxFactory");
			// properties.put(Context.PROVIDER_URL, "iiop://wgs97-74:1050");
			
			InitialContext ic = new InitialContext(properties);
			Object personRef = ic.lookup("ejb/AddressBook");
			
			AddressBookHome addressBookHome = (AddressBookHome) PortableRemoteObject.narrow(personRef, AddressBookHome.class);
			
			try {
				System.out.println("Creating new book: " + owner);
				addressBook = addressBookHome.create(owner);
				session.setAttribute(owner + "-" + ADDRESSBOOK_ATTR, addressBook);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void runCommand() throws NamingException {
		try {
			PeopleDTO peopleDto = addressBook.findByName(firstName, lastName);
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

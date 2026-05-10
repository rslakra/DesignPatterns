package com.rslakra.j2eepatterns.antipatterns.model;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.rslakra.j2eepatterns.antipatterns.ejbs.AddressBook;
import com.rslakra.j2eepatterns.antipatterns.ejbs.AddressBookHome;

public class CreateAddressCommand {
	private AddressBook[] books;
	private String[] names = { "Jack", "Jill", "Bill", "Ted" };
	
	public void initialize() throws NamingException {
		books = new AddressBook[names.length];
		
		Hashtable properties = new Properties();
		// properties.put(Context.INITIAL_CONTEXT_FACTORY,
		// "com.sun.jndi.cosnaming.CNCtxFactory");
		// properties.put(Context.PROVIDER_URL, "iiop://wgs97-74:1050");
		
		InitialContext ic = new InitialContext(properties);
		Object personRef = ic.lookup("ejb/AddressBook");
		
		AddressBookHome bookHome = (AddressBookHome) PortableRemoteObject.narrow(personRef, AddressBookHome.class);
		
		try {
			for (int i = 0; i < names.length; i++) {
				books[i] = bookHome.create(names[i]);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void runCommand() throws NamingException {
		try {
			for (int i = 0; i < 1000; i++) {
				int idx = (int) (Math.random() * names.length);
				String owner = names[idx];
				books[idx].addEntry(i, owner, owner, "tester" + i, "555-0" + i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		
	}
}

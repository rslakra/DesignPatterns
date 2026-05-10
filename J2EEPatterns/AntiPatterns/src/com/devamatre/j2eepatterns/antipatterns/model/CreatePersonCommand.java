package com.devamatre.j2eepatterns.antipatterns.model;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.devamatre.j2eepatterns.antipatterns.ejbs.EJBPersonHome;

public class CreatePersonCommand {
	private EJBPersonHome personHome;
	
	public void initialize() throws NamingException {
		Hashtable properties = new Properties();
		// properties.put(Context.INITIAL_CONTEXT_FACTORY,
		// "com.sun.jndi.cosnaming.CNCtxFactory");
		// properties.put(Context.PROVIDER_URL, "iiop://wgs97-74:1050");
		
		InitialContext ic = new InitialContext(properties);
		Object personRef = ic.lookup("ejb/EJBPerson");
		
		personHome = (EJBPersonHome) PortableRemoteObject.narrow(personRef, EJBPersonHome.class);
	}
	
	public void runCommand() throws NamingException {
		String[] names = { "Jack", "Jill", "Bill", "Ted" };
		try {
			for (int i = 0; i < 1000; i++) {
				personHome.create(i, "test", "tester" + i, "555-0" + i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		
	}
}

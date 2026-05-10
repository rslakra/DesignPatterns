package com.devamatre.j2eepatterns.antipatterns.ejbs;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2017-09-23 11:18:07 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AddressBook extends javax.ejb.EJBObject {
	
	public PeopleDTO findByName(java.lang.String firstName, java.lang.String lastName) throws java.rmi.RemoteException, FinderException;
	
	public void addEntry(int id, java.lang.String owner, java.lang.String firstName, java.lang.String lastName, java.lang.String phoneNumber) throws java.rmi.RemoteException, CreateException;
	
}

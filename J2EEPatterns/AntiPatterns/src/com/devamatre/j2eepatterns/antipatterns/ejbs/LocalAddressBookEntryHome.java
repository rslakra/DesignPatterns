package com.devamatre.j2eepatterns.antipatterns.ejbs;

import java.util.Collection;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface LocalAddressBookEntryHome extends EJBLocalHome {
	
	public LocalAddressBookEntry findByPrimaryKey(AddressBookEntryKey aKey) throws FinderException;
	
	public Collection findAll(String owner) throws FinderException;
	
	public Collection findByFirstName(String owner, String firstName) throws FinderException;
	
	public Collection findByLastName(String owner, String lastName) throws FinderException;
	
	public Collection findByName(String owner, String firstName, String lastName) throws javax.ejb.FinderException;
	
	public LocalAddressBookEntry create(int entryId, java.lang.String owner, java.lang.String firstName, java.lang.String lastName, java.lang.String phoneNumber) throws javax.ejb.CreateException;
	
}

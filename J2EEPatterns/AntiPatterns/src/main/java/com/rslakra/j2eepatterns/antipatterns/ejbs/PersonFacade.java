package com.rslakra.j2eepatterns.antipatterns.ejbs;

import javax.ejb.FinderException;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@rslakra.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface PersonFacade extends javax.ejb.EJBObject {
	
	public PeopleDTO findByName(java.lang.String firstName, java.lang.String lastName) throws java.rmi.RemoteException, FinderException;
	
}

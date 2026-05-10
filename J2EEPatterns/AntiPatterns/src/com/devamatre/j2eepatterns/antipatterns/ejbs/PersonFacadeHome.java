package com.devamatre.j2eepatterns.antipatterns.ejbs;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface PersonFacadeHome extends javax.ejb.EJBHome {
	
	public PersonFacade create() throws javax.ejb.CreateException, java.rmi.RemoteException;
	
}

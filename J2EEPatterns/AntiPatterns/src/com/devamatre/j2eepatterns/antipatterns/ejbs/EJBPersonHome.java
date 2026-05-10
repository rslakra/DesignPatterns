package com.devamatre.j2eepatterns.antipatterns.ejbs;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EJBPersonHome extends javax.ejb.EJBHome {
	
	public EJBPerson findByPrimaryKey(EJBPersonKey aKey) throws javax.ejb.FinderException, java.rmi.RemoteException;
	
	public java.util.Collection findByName(java.lang.String firstName, java.lang.String lastName) throws java.rmi.RemoteException, javax.ejb.FinderException;
	
	public EJBPerson create(int personId, java.lang.String firstName, java.lang.String lastName, java.lang.String phoneNumber) throws java.rmi.RemoteException, javax.ejb.CreateException;
	
	public java.util.Collection findAll() throws java.rmi.RemoteException, javax.ejb.FinderException;
	
	public java.util.Collection findByFirstName(java.lang.String firstName) throws java.rmi.RemoteException, javax.ejb.FinderException;
	
	public java.util.Collection findByLastName(java.lang.String lastName) throws java.rmi.RemoteException, javax.ejb.FinderException;
	
}

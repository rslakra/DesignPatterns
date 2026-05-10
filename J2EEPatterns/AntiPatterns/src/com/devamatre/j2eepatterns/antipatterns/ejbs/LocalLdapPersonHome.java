package com.devamatre.j2eepatterns.antipatterns.ejbs;

import java.util.Collection;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface LocalLdapPersonHome extends javax.ejb.EJBLocalHome {
	
	public LocalLdapPerson findByPrimaryKey(java.lang.String aKey) throws javax.ejb.FinderException;
	
	public Collection findByName(java.lang.String firstName, java.lang.String lastName) throws javax.ejb.FinderException;
	
}

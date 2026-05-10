package com.rslakra.j2eepatterns.antipatterns.ejbs;

import java.util.Collection;
import java.util.Properties;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@rslakra.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class LdapPersonBean implements EntityBean {
	private EntityContext context;
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private DirContext peopleContext;
	
	public void setEntityContext(EntityContext aContext) {
		context = aContext;
	}
	
	public void ejbActivate() {
		getPeopleContext();
	}
	
	public void ejbPassivate() {
		if (peopleContext != null) {
			try {
				peopleContext.close();
			} catch (NamingException ne) {
				throw new EJBException("Error passivating bean: " + ne, ne);
			} finally {
				peopleContext = null;
			}
		}
	}
	
	public void ejbRemove() {
	}
	
	public void unsetEntityContext() {
		context = null;
	}
	
	public void ejbLoad() {
		String key = (String) context.getPrimaryKey();
		try {
			loadFromLdap(key);
		} catch (NamingException ne) {
			throw new EJBException("Error loading " + key, ne);
		}
	}
	
	public void ejbStore() {
	}
	
	public String ejbFindByPrimaryKey(String key) throws FinderException {
		try {
			loadFromLdap(key);
			return (key);
		} catch (NamingException ne) {
			throw new FinderException("Error finding " + key + " : " + ne);
		}
	}
	
	public Collection ejbFindByName(String firstName, String lastName) throws FinderException {
		try {
			String ldapString = null;
			
			if (firstName != null && lastName != null) {
				ldapString = "& (cn=" + firstName + ") (sn=" + lastName + ")";
			} else if (firstName != null) {
				ldapString = "(cn=" + firstName + ")";
			} else if (lastName != null) {
				ldapString = "(sn=" + lastName + ")";
			} else {
				ldapString = "(objectClass=person)";
			}
			
			return ldapFind(ldapString);
		} catch (NamingException ne) {
			throw new FinderException("Error in find: " + ne);
		}
	}
	
	private NamingEnumeration ldapSearch(String filter) throws NamingException {
		SearchControls sc = new SearchControls();
		sc.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		
		NamingEnumeration people = getPeopleContext().search("", filter, sc);
		return people;
	}
	
	private void loadFromLdap(String key) throws NamingException {
		NamingEnumeration people = ldapSearch("cn=" + key);
		if (!people.hasMore())
			throw new NamingException("No entries found matching " + key);
		
		NameClassPair personName = (NameClassPair) people.next();
		Attributes personAttrs = peopleContext.getAttributes(personName.getName());
		Attribute cn = personAttrs.get("cn");
		Attribute sn = personAttrs.get("sn");
		Attribute phone = personAttrs.get("telephoneNumber");
		
		firstName = (String) cn.get();
		lastName = (String) sn.get();
		phoneNumber = (String) phone.get();
	}
	
	private Collection ldapFind(String filter) throws NamingException {
		Collection out = new Vector();
		
		NamingEnumeration people = ldapSearch(filter);
		while (people.hasMore()) {
			NameClassPair personName = (NameClassPair) people.next();
			Attributes personAttrs = peopleContext.getAttributes(personName.getName());
			Attribute cn = personAttrs.get("cn");
			out.add(cn.get());
		}
		
		return out;
	}
	
	private DirContext getPeopleContext() throws EJBException {
		if (peopleContext != null)
			return (peopleContext);
		
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost/o=jndiTest");
		env.put(Context.SECURITY_PRINCIPAL, "cn=Manager, o=jndiTest");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		
		try {
			DirContext initalContext = new InitialDirContext(env);
			peopleContext = (DirContext) initalContext.lookup("ou=people");
			return peopleContext;
		} catch (NamingException ne) {
			throw new EJBException("Error activating bean: " + ne, ne);
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
}

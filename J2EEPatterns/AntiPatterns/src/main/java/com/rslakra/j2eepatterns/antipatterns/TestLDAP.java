package com.rslakra.j2eepatterns.antipatterns;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class TestLDAP {
	final static String ldapServerName = "localhost";
	final static String rootdn = "cn=Manager, o=jndiTest";
	final static String rootpass = "secret";
	final static String rootContext = "o=jndiTest";
	
	public static void main(String[] args) {
		// set up environment to access the server
		
		Properties env = new Properties();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + ldapServerName + "/" + rootContext);
		env.put(Context.SECURITY_PRINCIPAL, rootdn);
		env.put(Context.SECURITY_CREDENTIALS, rootpass);
		
		try {
			// obtain initial directory context using the environment
			DirContext ctx = new InitialDirContext(env);
			DirContext people = (DirContext) ctx.lookup("ou=people");
			
			BasicAttributes mandatory = new BasicAttributes("cn", "schlomo");
			mandatory.put("objectClass", "person");
			mandatory.put("sn", "testa2");
			mandatory.put("telephoneNumber", "555-1212");
			
			people.createSubcontext("cn=schlomo", mandatory);
			
			NamingEnumeration ne = people.list("");
			while (ne.hasMore()) {
				NameClassPair ncp = (NameClassPair) ne.next();
				System.out.println("name: " + ncp.getName());
			}
			
		} catch (NameAlreadyBoundException nabe) {
			System.err.println("value has already been bound!");
			nabe.printStackTrace();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}

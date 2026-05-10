package com.devamatre.j2eepatterns.antipatterns;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NameAlreadyBoundException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class MakeRoot {
	final static String ldapServerName = "localhost";
	final static String rootdn = "cn=Manager, o=jndiTest";
	final static String rootpass = "secret";
	final static String rootContext = "o=jndiTest";
	
	public static void main(String[] args) {
		// set up environment to access the server
		
		Properties env = new Properties();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + ldapServerName + "/");
		env.put(Context.SECURITY_PRINCIPAL, rootdn);
		env.put(Context.SECURITY_CREDENTIALS, rootpass);
		
		try {
			// obtain initial directory context using the environment
			DirContext ctx = new InitialDirContext(env);
			
			// now, create the root context, which is just a subcontext
			// of this initial directory context.
			ctx.createSubcontext(rootContext);
		} catch (NameAlreadyBoundException nabe) {
			System.err.println(rootContext + " has already been bound!");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}

package com.rslakra.j2eepatterns.antipatterns.model;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

public interface PersonCommand {
	public void setFirstName(String firstName);
	
	public void setLastName(String lastName);
	
	public void initialize(HttpSession session) throws NamingException;
	
	public void runCommand() throws NamingException;
	
	public List getPeople();
}

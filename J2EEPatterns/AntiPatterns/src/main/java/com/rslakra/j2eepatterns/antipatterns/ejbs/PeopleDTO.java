package com.rslakra.j2eepatterns.antipatterns.ejbs;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@rslakra.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2003-02-01 11:18:23 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class PeopleDTO implements Serializable {
	private List people;
	
	public PeopleDTO() {
		people = new Vector();
	}
	
	public List getPeople() {
		return people;
	}
	
	public void addPerson(Person person) {
		people.add(person);
	}
}

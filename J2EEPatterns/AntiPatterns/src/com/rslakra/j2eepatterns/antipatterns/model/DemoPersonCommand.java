package com.rslakra.j2eepatterns.antipatterns.model;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import com.rslakra.j2eepatterns.antipatterns.ejbs.Person;

/**
 * In-memory {@link Person} list for local runs (Jetty) where EJB/JNDI is not available.
 */
public class DemoPersonCommand implements PersonCommand {

	private String firstName;
	private String lastName;
	private Vector people;

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public void initialize(HttpSession session) throws NamingException {
		people = new Vector();
	}

	@Override
	public void runCommand() throws NamingException {
		List<Person> all = Arrays.asList(
				new Person("Jane", "Doe", "555-0100"),
				new Person("John", "Smith", "555-0101"),
				new Person("Pat", "Lee", "555-0102"));
		people.clear();
		for (Person p : all) {
			if (matchesFilter(p)) {
				people.add(p);
			}
		}
	}

	private boolean matchesFilter(Person p) {
		boolean hasFirst = firstName != null && !firstName.isEmpty();
		boolean hasLast = lastName != null && !lastName.isEmpty();
		if (hasFirst && hasLast) {
			return firstName.equalsIgnoreCase(p.getFirstName()) && lastName.equalsIgnoreCase(p.getLastName());
		}
		if (hasFirst) {
			return firstName.equalsIgnoreCase(p.getFirstName());
		}
		if (hasLast) {
			return lastName.equalsIgnoreCase(p.getLastName());
		}
		return true;
	}

	@Override
	public List getPeople() {
		return people;
	}
}

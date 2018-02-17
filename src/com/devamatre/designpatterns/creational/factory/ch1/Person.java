package com.devamatre.designpatterns.creational.factory.ch1;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2007-01-03 03:29:27 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Person {
	public static final String MALE = "Male";
	public static final String FEMALE = "Female";

	private String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract String getSex();

	public String toString() {
		String value = "Hello, ";
		if (getSex().equals(MALE)) {
			value += "Mr. ";
		} else {
			value += "Mrs/Miss. ";
		}
		value += getName();
		return value;
	}
}

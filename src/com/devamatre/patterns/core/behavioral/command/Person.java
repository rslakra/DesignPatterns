package com.devamatre.patterns.core.behavioral.command;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-15 03:04:13 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class Person {

	private String name;
	private String address;
	private int yearOfBirth;

	public Person() {
		name = "unknown";
		address = "unknown";
		yearOfBirth = 0;
	}

	public String getName() {
		return name;
	}

	public String setName(String inputName) {
		name = inputName;
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String setAddress(String inputAddress) {
		address = inputAddress;
		return address;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public int setYearOfBirth(int inputYearOfBirth) {
		yearOfBirth = inputYearOfBirth;
		return yearOfBirth;
	}

	public String toString() {
		return "name: " + name + "\naddress: " + address + "\nYear of birth: " + yearOfBirth;
	}
}
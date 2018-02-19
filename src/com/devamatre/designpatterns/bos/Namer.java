/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.designpatterns.bos;

public class Namer {
	String firstName;
	String lastName;

	public Namer() {
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}

/**
 * Name is space delimited. We assume that everything before the last space is
 * part of the first name. If comma does not exists in name, the throw an error.
 */
class FirstName extends Namer {
	public FirstName(String str) {
		if (str == null) {
			throw new NullPointerException("Passed Name is Null!");
		}
		int idx = str.lastIndexOf(" ");
		if (idx > 0) {
			firstName = str.substring(0, idx).trim();
			lastName = str.substring(idx + 1).trim();
		} else {
			lastName = str;
			firstName = "";
		}
	}
}

/**
 * Name is comma delimited. Before comman is LastName and after it is FirstName,
 * if comma does not exists in name, the throw an error.
 */
class LastName extends Namer {
	public LastName(String str) {
		if (str == null) {
			throw new NullPointerException("Passed Name is Null!");
		}
		int idx = str.indexOf(",");
		if (idx > 0) {
			lastName = str.substring(0, idx).trim();
			firstName = str.substring(idx + 1).trim();
		} else {
			lastName = str;
			firstName = "";
		}
	}
}

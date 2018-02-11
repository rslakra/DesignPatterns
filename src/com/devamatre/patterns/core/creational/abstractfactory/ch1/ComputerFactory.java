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
package com.devamatre.patterns.core.creational.abstractfactory.ch1;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2007-01-04 03:10:24 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class ComputerFactory {
	/* Designed on the singleton pattern and is thread safe */
	private static ComputerFactory instance = new ComputerFactory();
	public static final String PC = "PC";
	public static final String WORKSTATION = "Workstation";
	public static final String SERVER = "Server";

	/* Default Constructor */
	private ComputerFactory() {
	}

	/**
	 * This method returns a thread safe instance of ComputerFactory.
	 *
	 * @return an instance of ComputerFactory
	 */
	public synchronized static ComputerFactory getInstance() {
		return instance;
	}

	public Computer getComputer(String type) {
		Computer computer = null;
		if (type.equals(PC)) {
			computer = new PC();
		} else if (type.equals(WORKSTATION)) {
			computer = new Workstation();
		} else if (type.equals(SERVER)) {
			computer = new Server();
		} else {
			throw new IllegalArgumentException("Mismatched Parameter Type : " + type);
		}
		return computer;
	}

}

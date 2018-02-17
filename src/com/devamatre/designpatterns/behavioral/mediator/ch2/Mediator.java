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
package com.devamatre.designpatterns.behavioral.mediator.ch2;

/**
 * Prrpose : Meditar Design Pattern 1. Createa an "Intermediatory" that
 * decouples "Producers" and "Consumers" 2. Produers are coupled only to the
 * Mediator. 3. Consumers are coupled only to the Mediator. 4. The Mediator
 * arbitrates storing and reteriving of message.
 * 
 * Mediator centeralizes arbitrary communication between colleges objects.
 * 
 * Intent:- Define an object that encapsulates how a set of obejcts interact.
 * Mediator promotes loose coupling by keeping objects from referring to each
 * other explicitly, and it lets you vary their interaction independently.
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2005-12-10 03:08:27 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class Mediator {

	private boolean slotFull = false; // 4. The Mediator arbitrates
	private int number;

	/**
	 * 
	 * @param num
	 */
	public synchronized void storeMessage(int num) {
		while (slotFull == true) // no room for another message
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("e : " + e);
			}
		slotFull = true;
		number = num;
		notifyAll();
	}

	/**
	 * 
	 * @return
	 */
	public synchronized int retrieveMessage() {
		while (slotFull == false) // no message to retrieve
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("e : " + e);
			}
		slotFull = false;
		notifyAll();
		return number;
	}
}

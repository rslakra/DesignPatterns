/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
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

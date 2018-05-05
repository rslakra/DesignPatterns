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
package com.devamatre.designpatterns.behavioral.mediator.ch3;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2009-11-23 10:09:14 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestContactMediator {
	/**
	 * 
	 * @param arguments
	 */
	public static void main(String[] arguments) {
		System.out.println("Mediator Pattern Example");
		System.out.println("In this demonstration, the ContactMediatorImpl class will");
		System.out.println(" coordinate updates between three controls in a GUI - the");
		System.out.println(" ContactDisplayPanel, the ContactEditorPanel, and the");
		System.out.println(" ContactSelectorPanel. As its name suggests, the Mediator");
		System.out.println(" mediates the activity between the elements of the GUI,");
		System.out.println(" translating method calls from one panel into the appropriate");
		System.out.println(" method calls on the other GUI components.");
		Contact contact = new ContactImpl("", "", "", "");
		Contact contact1 = new ContactImpl("Duke", "", "Java Advocate", "The Patterns Guild");
		ContactMediatorImpl mediator = new ContactMediatorImpl();
		mediator.addContact(contact);
		mediator.addContact(contact1);
		GUIMediator guiMediator = new GUIMediator();
		guiMediator.setContactMediator(mediator);
		guiMediator.createGui();
	}
}
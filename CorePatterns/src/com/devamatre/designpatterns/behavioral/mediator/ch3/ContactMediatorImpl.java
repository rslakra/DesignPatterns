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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2005-11-23 10:09:14 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class ContactMediatorImpl implements ContactMediator {
	private ContactDisplayPanel display;
	private ContactEditorPanel editor;
	private ContactSelectorPanel selector;
	private List<Contact> contacts = new ArrayList<Contact>();
	private int contactIndex;
	
	/**
	 * 
	 * @param displayPanel
	 * @see com.devamatre.designpatterns.behavioral.mediator.ch3.ContactMediator#setContactDisplayPanel(com.devamatre.designpatterns.behavioral.mediator.ch3.ContactDisplayPanel)
	 */
	public void setContactDisplayPanel(ContactDisplayPanel displayPanel) {
		display = displayPanel;
	}
	
	/**
	 * 
	 * @param editorPanel
	 * @see com.devamatre.designpatterns.behavioral.mediator.ch3.ContactMediator#setContactEditorPanel(com.devamatre.designpatterns.behavioral.mediator.ch3.ContactEditorPanel)
	 */
	public void setContactEditorPanel(ContactEditorPanel editorPanel) {
		editor = editorPanel;
	}
	
	/**
	 * 
	 * @param selectorPanel
	 * @see com.devamatre.designpatterns.behavioral.mediator.ch3.ContactMediator#setContactSelectorPanel(com.devamatre.designpatterns.behavioral.mediator.ch3.ContactSelectorPanel)
	 */
	public void setContactSelectorPanel(ContactSelectorPanel selectorPanel) {
		selector = selectorPanel;
	}
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param title
	 * @param organization
	 * @see com.devamatre.designpatterns.behavioral.mediator.ch3.ContactMediator#createContact(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public void createContact(String firstName, String lastName, String title, String organization) {
		Contact newContact = new ContactImpl(firstName, lastName, title, organization);
		addContact(newContact);
		selector.addContact(newContact);
		display.contactChanged(newContact);
	}
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param title
	 * @param organization
	 * @see com.devamatre.designpatterns.behavioral.mediator.ch3.ContactMediator#updateContact(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public void updateContact(String firstName, String lastName, String title, String organization) {
		Contact updateContact = (Contact) contacts.get(contactIndex);
		if (updateContact != null) {
			updateContact.setFirstName(firstName);
			updateContact.setLastName(lastName);
			updateContact.setTitle(title);
			updateContact.setOrganization(organization);
			display.contactChanged(updateContact);
		}
	}
	
	public void selectContact(Contact contact) {
		if (contacts.contains(contact)) {
			contactIndex = contacts.indexOf(contact);
			display.contactChanged(contact);
			editor.setContactFields(contact);
		}
	}
	
	public Contact[] getAllContacts() {
		return (Contact[]) contacts.toArray(new Contact[1]);
	}
	
	public void addContact(Contact contact) {
		if (!contacts.contains(contact)) {
			contacts.add(contact);
		}
	}
}
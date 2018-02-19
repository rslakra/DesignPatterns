/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2018.
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
	private List<Contact> contacts = new ArrayList();
	private int contactIndex;

	public void setContactDisplayPanel(ContactDisplayPanel displayPanel) {
		display = displayPanel;
	}

	public void setContactEditorPanel(ContactEditorPanel editorPanel) {
		editor = editorPanel;
	}

	public void setContactSelectorPanel(ContactSelectorPanel selectorPanel) {
		selector = selectorPanel;
	}

	public void createContact(String firstName, String lastName, String title, String organization) {
		Contact newContact = new ContactImpl(firstName, lastName, title, organization);
		addContact(newContact);
		selector.addContact(newContact);
		display.contactChanged(newContact);
	}

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
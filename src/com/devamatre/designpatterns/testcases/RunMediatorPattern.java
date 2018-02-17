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
package com.devamatre.designpatterns.testcases;

/**
 * @author rohtash.singh
 * Created on Nov 23, 2005
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//[C] 2002 Sun Microsystems, Inc.---

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RunMediatorPattern {
	public static void main(String[] arguments) {
		System.out.println("Example for the Mediator pattern");
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
		MediatorGui gui = new MediatorGui();
		gui.setContactMediator(mediator);
		gui.createGui();
	}
}

class ContactEditorPanel extends JPanel implements ActionListener {
	private ContactMediator mediator;
	private JTextField firstName, lastName, title, organization;
	private JButton create, update;

	public ContactEditorPanel() {
		createGui();
	}

	public ContactEditorPanel(ContactMediator newMediator) {
		setContactMediator(newMediator);
		createGui();
	}

	public void createGui() {
		setLayout(new BorderLayout());
		JPanel editor = new JPanel();
		editor.setLayout(new GridLayout(4, 2));
		editor.add(new JLabel("First Name:"));
		firstName = new JTextField(20);
		editor.add(firstName);
		editor.add(new JLabel("Last Name:"));
		lastName = new JTextField(20);
		editor.add(lastName);
		editor.add(new JLabel("Title:"));
		title = new JTextField(20);
		editor.add(title);
		editor.add(new JLabel("Organization:"));
		organization = new JTextField(20);
		editor.add(organization);
		add(editor, BorderLayout.CENTER);
		JPanel control = new JPanel();
		create = new JButton("Create Contact");
		update = new JButton("Update Contact");
		create.addActionListener(this);
		update.addActionListener(this);
		control.add(create);
		control.add(update);
		add(control, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == create) {
			createContact();
		} else if (source == update) {
			updateContact();
		}
	}

	public void createContact() {
		mediator.createContact(firstName.getText(), lastName.getText(), title.getText(), organization.getText());
	}

	public void updateContact() {
		mediator.updateContact(firstName.getText(), lastName.getText(), title.getText(), organization.getText());
	}

	public void setContactFields(Contact contact) {
		firstName.setText(contact.getFirstName());
		lastName.setText(contact.getLastName());
		title.setText(contact.getTitle());
		organization.setText(contact.getOrganization());
	}

	public void setContactMediator(ContactMediator newMediator) {
		mediator = newMediator;
	}
}

class ContactMediatorImpl implements ContactMediator {
	private ContactDisplayPanel display;
	private ContactEditorPanel editor;
	private ContactSelectorPanel selector;
	private ArrayList contacts = new ArrayList();
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

class ContactDisplayPanel extends JPanel {
	private ContactMediator mediator;
	private JTextArea displayRegion;

	public ContactDisplayPanel() {
		createGui();
	}

	public ContactDisplayPanel(ContactMediator newMediator) {
		setContactMediator(newMediator);
		createGui();
	}

	public void createGui() {
		setLayout(new BorderLayout());
		displayRegion = new JTextArea(10, 40);
		displayRegion.setEditable(false);
		add(new JScrollPane(displayRegion));
	}

	public void contactChanged(Contact contact) {
		displayRegion.setText("Contact\n\tName: " + contact.getFirstName() + " " + contact.getLastName() + "\n\tTitle: "
				+ contact.getTitle() + "\n\tOrganization: " + contact.getOrganization());
	}

	public void setContactMediator(ContactMediator newMediator) {
		mediator = newMediator;
	}
}

class ContactSelectorPanel extends JPanel implements ActionListener {
	private ContactMediator mediator;
	private JComboBox selector;

	public ContactSelectorPanel() {
		createGui();
	}

	public ContactSelectorPanel(ContactMediator newMediator) {
		setContactMediator(newMediator);
		createGui();
	}

	public void createGui() {
		selector = new JComboBox(mediator.getAllContacts());
		selector.addActionListener(this);
		add(selector);
	}

	public void actionPerformed(ActionEvent evt) {
		mediator.selectContact((Contact) selector.getSelectedItem());
	}

	public void addContact(Contact contact) {
		selector.addItem(contact);
		selector.setSelectedItem(contact);
	}

	public void setContactMediator(ContactMediator newMediator) {
		mediator = newMediator;
	}
}

class MediatorGui {
	private ContactMediator mediator;

	public void setContactMediator(ContactMediator newMediator) {
		mediator = newMediator;
	}

	public void createGui() {
		JFrame mainFrame = new JFrame("Mediator example");
		Container content = mainFrame.getContentPane();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		ContactSelectorPanel select = new ContactSelectorPanel(mediator);
		ContactDisplayPanel display = new ContactDisplayPanel(mediator);
		ContactEditorPanel edit = new ContactEditorPanel(mediator);
		content.add(select);
		content.add(display);
		content.add(edit);
		mediator.setContactSelectorPanel(select);
		mediator.setContactDisplayPanel(display);
		mediator.setContactEditorPanel(edit);
		mainFrame.addWindowListener(new WindowCloseManager());
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private class WindowCloseManager extends WindowAdapter {
		public void windowClosing(WindowEvent evt) {
			System.exit(0);
		}
	}
}

interface ContactMediator {
	public void setContactDisplayPanel(ContactDisplayPanel displayPanel);

	public void setContactEditorPanel(ContactEditorPanel editorPanel);

	public void setContactSelectorPanel(ContactSelectorPanel selectorPanel);

	public void createContact(String firstName, String lastName, String title, String organization);

	public void updateContact(String firstName, String lastName, String title, String organization);

	public Contact[] getAllContacts();

	public void selectContact(Contact contact);
}

interface Contact extends Serializable {
	public static final String SPACE = " ";

	public String getFirstName();

	public String getLastName();

	public String getTitle();

	public String getOrganization();

	public void setFirstName(String newFirstName);

	public void setLastName(String newLastName);

	public void setTitle(String newTitle);

	public void setOrganization(String newOrganization);
}

class ContactImpl implements Contact {
	private String firstName;
	private String lastName;
	private String title;
	private String organization;

	public ContactImpl() {
	}

	public ContactImpl(String newFirstName, String newLastName, String newTitle, String newOrganization) {
		firstName = newFirstName;
		lastName = newLastName;
		title = newTitle;
		organization = newOrganization;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getTitle() {
		return title;
	}

	public String getOrganization() {
		return organization;
	}

	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}

	public void setLastName(String newLastName) {
		lastName = newLastName;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setOrganization(String newOrganization) {
		organization = newOrganization;
	}

	public String toString() {
		return firstName + SPACE + lastName;
	}
}

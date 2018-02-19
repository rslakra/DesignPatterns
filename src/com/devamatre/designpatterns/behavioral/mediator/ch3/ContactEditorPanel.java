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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2005-11-23 10:09:14 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class ContactEditorPanel extends JPanel implements ActionListener {
	
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
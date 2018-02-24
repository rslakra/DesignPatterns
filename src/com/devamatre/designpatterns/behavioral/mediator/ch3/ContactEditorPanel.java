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
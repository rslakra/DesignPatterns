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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2005-11-23 10:09:14 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class ContactDisplayPanel extends JPanel {
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
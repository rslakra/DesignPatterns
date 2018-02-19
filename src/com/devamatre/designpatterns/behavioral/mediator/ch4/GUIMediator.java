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
package com.devamatre.designpatterns.behavioral.mediator.ch4;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2005-11-23 10:09:14 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class GUIMediator extends JFrame {

	public static final String NEWLINE = "\n";

	public static final String UPLOAD = "Upload";
	public static final String DOWNLOAD = "Download";
	public static final String DELETE = "Delete";
	public static final String EXIT = "Exit";

	private JPanel rootPanel;

	private LocalList localList;
	private RemoteList remoteList;

	private DefaultListModel localListModel;
	private DefaultListModel remoteListModel;

	private Mediator mediator = new Mediator();

	public GUIMediator() throws Exception {
		super("Design Patterns By Example");
		guiInitializer();

		localList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		localList.setSelectedIndex(-1);
		JScrollPane localListScrollPane = new JScrollPane(localList);

		remoteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		remoteList.setSelectedIndex(-1);
		JScrollPane remmoteListScrollPane = new JScrollPane(remoteList);

		// Create Labels
		JLabel lblLocalList = new JLabel("Local List:");
		JLabel lblRemoteList = new JLabel("Remote List:");
		JLabel lblSpacer = new JLabel("         ");

		// Create buttons
		UploadButton btnUpload = new UploadButton(GUIMediator.UPLOAD, mediator);
		btnUpload.setMnemonic(KeyEvent.VK_U);
		DownloadButton btnDownload = new DownloadButton(GUIMediator.DOWNLOAD, mediator);
		btnDownload.setMnemonic(KeyEvent.VK_N);
		DeleteButton btnDelete = new DeleteButton(GUIMediator.DELETE, mediator);
		btnDelete.setMnemonic(KeyEvent.VK_D);
		JButton btnExit = new JButton(GUIMediator.EXIT);
		btnExit.setMnemonic(KeyEvent.VK_X);

		ButtonHandler buttonHandler = new ButtonHandler();
		ListHandler listHandler = new ListHandler();

		btnUpload.addActionListener(buttonHandler);
		btnDownload.addActionListener(buttonHandler);
		btnDelete.addActionListener(buttonHandler);
		btnExit.addActionListener(buttonHandler);
		localList.addListSelectionListener(listHandler);
		remoteList.addListSelectionListener(listHandler);

		JPanel lstPanel = new JPanel();

		GridBagLayout gridbag2 = new GridBagLayout();
		lstPanel.setLayout(gridbag2);
		GridBagConstraints gbc2 = new GridBagConstraints();
		lstPanel.add(lblLocalList);
		lstPanel.add(lblRemoteList);
		lstPanel.add(localListScrollPane);
		lstPanel.add(remmoteListScrollPane);
		lstPanel.add(lblSpacer);

		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gridbag2.setConstraints(lblLocalList, gbc2);
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		gridbag2.setConstraints(lblSpacer, gbc2);

		gbc2.gridx = 5;
		gbc2.gridy = 0;
		gridbag2.setConstraints(lblRemoteList, gbc2);
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		gridbag2.setConstraints(localListScrollPane, gbc2);
		gbc2.gridx = 5;
		gbc2.gridy = 1;
		gridbag2.setConstraints(remmoteListScrollPane, gbc2);

		// -----------------------------------
		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel();

		// ----------------------------------------------
		GridBagLayout gridbag = new GridBagLayout();
		buttonPanel.setLayout(gridbag);
		GridBagConstraints gbc = new GridBagConstraints();
		buttonPanel.add(lstPanel);
		buttonPanel.add(btnUpload);
		buttonPanel.add(btnDownload);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnExit);

		gbc.insets.top = 5;
		gbc.insets.bottom = 5;
		gbc.insets.left = 5;
		gbc.insets.right = 5;

		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gridbag.setConstraints(btnUpload, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gridbag.setConstraints(btnDownload, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gridbag.setConstraints(btnDelete, gbc);
		gbc.gridx = 4;
		gbc.gridy = 0;
		gridbag.setConstraints(btnExit, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gridbag.setConstraints(lstPanel, gbc);

		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets.left = 2;
		gbc.insets.right = 2;
		gbc.insets.top = 40;

		// ****************************************************
		// Add the buttons and the log to the frame
		Container contentPane = getContentPane();
		contentPane.add(lstPanel, BorderLayout.CENTER);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		btnUpload.setEnabled(false);
		btnDelete.setEnabled(false);
		btnDownload.setEnabled(false);

		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
			SwingUtilities.updateComponentTreeUI(GUIMediator.this);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Initialize the GUI.
	 */
	private void guiInitializer() {
		// Create controls
		rootPanel = new JPanel();

		localListModel = new DefaultListModel();
		// fill some test data here into the listbox.
		localListModel.addElement("htmls/first.html");
		localListModel.addElement("htmls/second.html");
		localListModel.addElement("htmls/third.html");
		localListModel.addElement("htmls/four.html");
		localListModel.addElement("htmls/five.html");
		localListModel.addElement("htmls/index.html");
		localList = new LocalList(localListModel, mediator);

		remoteListModel = new DefaultListModel();
		remoteListModel.addElement("htmls/six.html");
		remoteListModel.addElement("htmls/seven.html");
		remoteListModel.addElement("htmls/eight.html");
		remoteListModel.addElement("htmls/nine.html");
		remoteListModel.addElement("htmls/footer.html");
		remoteList = new RemoteList(remoteListModel, mediator);
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		JFrame frame = new GUIMediator();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// frame.pack();
		frame.setSize(450, 300);
		frame.setVisible(true);
	}

	/**
	 * 
	 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
	 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
	 * @created 2018-02-19 10:28:57 AM
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	private class ListHandler implements ListSelectionListener {
		/**
		 * 
		 * @param e
		 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e) {
			Command command = (Command) e.getSource();
			command.processEvent();
		}
	}

	/**
	 * 
	 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
	 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
	 * @created 2018-02-19 10:28:55 AM
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	private class ButtonHandler implements ActionListener {
		/**
		 * 
		 * @param e
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals(GUIMediator.EXIT)) {
				System.exit(1);
			}

			Command command = (Command) e.getSource();
			command.processEvent();
		}
	}

}

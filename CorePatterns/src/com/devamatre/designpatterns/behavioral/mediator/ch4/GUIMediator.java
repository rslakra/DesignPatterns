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
package com.devamatre.designpatterns.behavioral.mediator.ch4;

import com.devamatre.swing.SwingUtility;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2005-11-23 10:09:14 AM
 * @since 1.0.0
 */
public class GUIMediator extends JFrame {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public static final String NEWLINE = "\n";

    public static final String UPLOAD = "Upload";
    public static final String DOWNLOAD = "Download";
    public static final String DELETE = "Delete";
    public static final String EXIT = "Exit";

    // private JPanel rootPanel;

    private LocalList localList;
    private RemoteList remoteList;

    private DefaultListModel<String> localListModel;
    private DefaultListModel<String> remoteListModel;
    private Mediator mediator = new Mediator();

    /**
     * @throws Exception
     */
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
            SwingUtility.setSystemLookAndFeel();
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
        // rootPanel = new JPanel();

        localListModel = new DefaultListModel<String>();
        // fill some test data here into the listbox.
        localListModel.addElement("htmls/first.html");
        localListModel.addElement("htmls/second.html");
        localListModel.addElement("htmls/third.html");
        localListModel.addElement("htmls/four.html");
        localListModel.addElement("htmls/five.html");
        localListModel.addElement("htmls/index.html");
        localList = new LocalList(localListModel, mediator);

        remoteListModel = new DefaultListModel<String>();
        remoteListModel.addElement("htmls/six.html");
        remoteListModel.addElement("htmls/seven.html");
        remoteListModel.addElement("htmls/eight.html");
        remoteListModel.addElement("htmls/nine.html");
        remoteListModel.addElement("htmls/footer.html");
        remoteList = new RemoteList(remoteListModel, mediator);
    }

    /**
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
     * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
     * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
     * @version 1.0.0
     * @created 2018-02-19 10:28:57 AM
     * @since 1.0.0
     */
    private class ListHandler implements ListSelectionListener {
        /**
         * @param e
         * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
         */
        public void valueChanged(ListSelectionEvent e) {
            Command command = (Command) e.getSource();
            command.processEvent();
        }
    }

    /**
     * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
     * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
     * @version 1.0.0
     * @created 2018-02-19 10:28:55 AM
     * @since 1.0.0
     */
    private class ButtonHandler implements ActionListener {
        /**
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

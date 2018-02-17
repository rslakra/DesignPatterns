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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

/**
 * @author rohtash.singh
 * Created on Nov 23, 2005
 */

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-17 10:52:41 AM 
 * @version 1.0.0
 * @since 1.0.0
 */
public class StandardDialogTest extends StandardDialog {

	public StandardDialogTest() {
		super();
	}

	public boolean showApplyButton() {
		return false;
	};

	public void apply() {
	};

	public static void main(String[] args) {
		new StandardDialogTest();
	}

}

abstract class StandardDialog extends JDialog {

	public StandardDialog() throws HeadlessException {
		super();
		_init();
	}

	public StandardDialog(Dialog owner) throws HeadlessException {
		super(owner);
		_init();
	}

	public StandardDialog(Dialog owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		_init();
	}

	public StandardDialog(Frame owner) throws HeadlessException {
		super(owner);
		_init();
	}

	public StandardDialog(Frame owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		_init();
	}

	public StandardDialog(Dialog owner, String title) throws HeadlessException {
		super(owner, title);
		_init();
	}

	public StandardDialog(Dialog owner, String title, boolean modal) throws HeadlessException {
		super(owner, title, modal);
		_init();
	}

	public StandardDialog(Frame owner, String title) throws HeadlessException {
		super(owner, title);
		_init();
	}

	public StandardDialog(Frame owner, String title, boolean modal) throws HeadlessException {
		super(owner, title, modal);
		_init();
	}

	public StandardDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc)
			throws HeadlessException {
		super(owner, title, modal, gc);
		_init();
	}

	public StandardDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		_init();
	}

	public void setMainContent(Component c) {
		mContent.add(c, BorderLayout.CENTER);
		mMainPane = c;
	}

	protected void _init() {
		mContent = getContentPane();
		mContent.setLayout(new BorderLayout());
		mButtonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mContent.add(mButtonPane, BorderLayout.SOUTH);
		mButtonListener = new ButtonListener();
		if (showApplyButton()) {
			mApply = new JButton("Apply");
			mButtonPane.add(mApply);
			mApply.addActionListener(mButtonListener);
		}
		mOk = new JButton("OK");
		mButtonPane.add(mOk);
		mOk.addActionListener(mButtonListener);
		mCancel = new JButton("Cancel");
		mButtonPane.add(mCancel);
		mCancel.addActionListener(mButtonListener);

		// mMainPane = new JPanel();
		// mContent.add(mMainPane, BorderLayout.CENTER);
	}

	public Component getMainPane() {
		return mMainPane;
	}

	// public void setMainPane(Component c) {
	// mMainPane = c;
	// }

	protected void ok() {
		mCancelState = OK;
		apply();
		close();
	}

	protected void cancel() {
		mCancelState = CANCEL;
		close();
	}

	protected void close() {
		dispose();
	}

	public abstract boolean showApplyButton();

	public abstract void apply();

	public void dispose() {
		if (mCancelState != OK) {
			mCancelState = CANCEL;
		}
		synchronized (this) {
			notifyAll();
		}
		super.dispose();
	}

	protected class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JButton b = (JButton) ae.getSource();
			if (b == mApply) {
				apply();
			} else if (b == mOk) {
				ok();
			} else if (b == mCancel) {
				cancel();
			}
		}
	}

	public int getCancelState() {
		return mCancelState;
	}

	public static int showStandardDialog(StandardDialog sd) {
		Window w = sd.getOwner();
		if (w == null) {
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension ps = sd.getPreferredSize();
			return showStandardDialog(sd, (d.width - ps.width) / 2, (d.height - ps.height) / 2);
		} else {
			Point p = w.getLocation();
			Dimension d = w.getSize();
			return showStandardDialog(sd, p.x + 100, p.y + 20);
		}
	}

	public static int showStandardDialog(StandardDialog sd, int x, int y) {
		sd.setLocation(x, y);
		sd.pack();
		sd.setVisible(true);
		return sd.getCancelState();
	}

	public static final int OK = 1;
	public static final int SHOWN = 0;
	public static final int CANCEL = -1;

	private int mCancelState = 0;
	protected boolean mBlockInCode;
	protected Container mContent;
	protected Component mMainPane;
	protected JButton mApply;
	protected JButton mOk;
	protected JButton mCancel;
	protected JPanel mButtonPane;
	protected ActionListener mButtonListener;
}

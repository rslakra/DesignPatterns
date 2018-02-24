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
package com.devamatre.designpatterns;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
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
 * @created 2005-11-23 10:52:41 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestStandardDialog extends StandardDialog {

	public TestStandardDialog() {
		super();
	}

	public boolean showApplyButton() {
		return false;
	};

	public void apply() {
	};

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new TestStandardDialog();
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

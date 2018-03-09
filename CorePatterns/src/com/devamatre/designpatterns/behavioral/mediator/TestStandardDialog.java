/*
 * Created on Nov 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.devamatre.designpatterns.behavioral.mediator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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

public class TestStandardDialog extends StandardDialog {
	
	public TestStandardDialog() {
		super();
	}
	
	public boolean showApplyButton() {
		return false;
	};
	
	public void apply() {
	};
	
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
	
	public StandardDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) throws HeadlessException {
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

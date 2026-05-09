package com.rslakra.swing;

import javax.swing.UIManager;

/**
 * Minimal stand-in for the legacy dSwing helper (GUI mediator example only).
 */
public final class SwingUtility {

    private SwingUtility() {
    }

    public static void setSystemLookAndFeel() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
}

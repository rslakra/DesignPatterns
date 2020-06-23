package com.devamatre.designpatterns.behavioral.memento;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:37 PM
 * Version: 1.0.0
 */
public class TextEditor {
    private TextWindow textWindow;
    private Memento savedTextWindow;

    public TextEditor(TextWindow textWindow) {
        this.textWindow = textWindow;
    }

    public void write(String text) {
        textWindow.addText(text);
    }

    public String print() {
        return textWindow.getCurrent();
    }

    public void hitSave() {
        savedTextWindow = textWindow.save();
    }

    public void hitUndo() {
        textWindow.restore(savedTextWindow);
    }
}

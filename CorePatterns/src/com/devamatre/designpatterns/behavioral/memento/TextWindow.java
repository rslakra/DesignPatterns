package com.devamatre.designpatterns.behavioral.memento;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:37 PM
 * Version: 1.0.0
 */
public class TextWindow {
    private StringBuilder textBuilder;

    public TextWindow() {
        this.textBuilder = new StringBuilder();
    }

    public String getCurrent() {
        return textBuilder.toString();
    }

    public void addText(String text) {
        textBuilder.append(text);
    }

    public Memento save() {
        return new Memento(textBuilder.toString());
    }

    public void restore(Memento save) {
        textBuilder = new StringBuilder(save.getText());
    }
}

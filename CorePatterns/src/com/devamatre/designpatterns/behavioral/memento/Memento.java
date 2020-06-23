package com.devamatre.designpatterns.behavioral.memento;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:38 PM
 * Version: 1.0.0
 */
public class Memento {
    private String text;

    public Memento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

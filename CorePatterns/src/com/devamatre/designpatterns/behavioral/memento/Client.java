package com.devamatre.designpatterns.behavioral.memento;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:41 PM
 * Version: 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(new TextWindow());
        textEditor.write("The Memento Design Pattern\n");
        textEditor.write("How to implement it in Java?\n");
        textEditor.hitSave();

        textEditor.write("Buy Grocery before coming home\n");
        textEditor.hitUndo();

        System.out.println(textEditor.print());
        System.out.println(textEditor.print().equals("The Memento Design Pattern\nHow to implement it in Java?\n"));
    }

}

package com.devamatre.designpatterns.behavioral.interpreter;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:01 PM
 * Version: 1.0.0
 */
public class Row {

    private String name;
    private String surname;

    Row(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
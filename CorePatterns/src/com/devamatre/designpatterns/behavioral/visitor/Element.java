package com.devamatre.designpatterns.behavioral.visitor;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:52 PM
 * Version: 1.0.0
 */
public abstract class Element {

    public String uuid;

    public Element(String uuid) {
        this.uuid = uuid;
    }

    public abstract void accept(Visitor v);
}
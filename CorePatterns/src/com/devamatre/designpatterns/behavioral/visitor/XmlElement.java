package com.devamatre.designpatterns.behavioral.visitor;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:55 PM
 * Version: 1.0.0
 */
public class XmlElement extends Element {

    public XmlElement(String uuid) {
        super(uuid);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
package com.devamatre.designpatterns.behavioral.visitor;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:54 PM
 * Version: 1.0.0
 */
public class ElementVisitor implements Visitor {

    @Override
    public void visit(XmlElement xe) {
        System.out.println("processing xml element with uuid: " + xe.uuid);
    }

    @Override
    public void visit(JsonElement je) {
        System.out.println("processing json element with uuid: " + je.uuid);
    }
}
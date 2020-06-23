package com.devamatre.designpatterns.behavioral.visitor;

import java.util.UUID;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:55 PM
 * Version: 1.0.0
 */
public class VisitorMain {

    public static void main(String[] args) {

        Visitor v = new ElementVisitor();

        Document d = new Document(generateUuid());
        d.elements.add(new JsonElement(generateUuid()));
        d.elements.add(new JsonElement(generateUuid()));
        d.elements.add(new XmlElement(generateUuid()));

        d.accept(v);
    }

    private static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
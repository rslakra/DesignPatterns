package com.devamatre.designpatterns.behavioral.visitor;

import java.util.UUID;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:55 PM
 * Version: 1.0.0
 */
public class VisitorMain {

    public static void main(String[] args) {

        Visitor visitor = new ElementVisitor();

        Document document = new Document(generateUuid());
        document.elements.add(new JsonElement(generateUuid()));
        document.elements.add(new JsonElement(generateUuid()));
        document.elements.add(new XmlElement(generateUuid()));

        document.accept(visitor);
    }

    private static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
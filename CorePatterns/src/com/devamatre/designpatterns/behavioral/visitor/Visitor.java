package com.devamatre.designpatterns.behavioral.visitor;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:51 PM
 * Version: 1.0.0
 */
public interface Visitor {

    /**
     * @param xmlElement
     */
    void visit(XmlElement xmlElement);

    /**
     * @param jsonElement
     */
    void visit(JsonElement jsonElement);
}
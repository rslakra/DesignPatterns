package com.devamatre.designpatterns.behavioral.visitor;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:52 PM
 * Version: 1.0.0
 */

import java.util.ArrayList;
import java.util.List;

public class Document extends Element {

    List<Element> elements = new ArrayList<>();

    public Document(String uuid) {
        super(uuid);
    }

    @Override
    public void accept(Visitor v) {
        for (Element e : this.elements) {
            e.accept(v);
        }
    }
}
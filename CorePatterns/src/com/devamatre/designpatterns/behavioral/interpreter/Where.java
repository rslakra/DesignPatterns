package com.devamatre.designpatterns.behavioral.interpreter;

import java.util.List;
import java.util.function.Predicate;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:02 PM
 * Version: 1.0.0
 */
public class Where implements Expression {

    private Predicate<String> filter;

    Where(Predicate<String> filter) {
        this.filter = filter;
    }

    @Override
    public List<String> interpret(Context ctx) {
        ctx.setFilter(filter);
        return ctx.search();
    }
}
package com.devamatre.designpatterns.behavioral.interpreter;

import java.util.List;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:03 PM
 * Version: 1.0.0
 */
public class Select implements Expression {

    private String column;
    private From from;

    Select(String column, From from) {
        this.column = column;
        this.from = from;
    }

    @Override
    public List<String> interpret(Context ctx) {
        ctx.setColumn(column);
        return from.interpret(ctx);
    }
}
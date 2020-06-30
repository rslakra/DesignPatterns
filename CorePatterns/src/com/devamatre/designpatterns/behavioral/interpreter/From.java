package com.devamatre.designpatterns.behavioral.interpreter;

import java.util.List;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:02 PM
 * Version: 1.0.0
 */
class From implements Expression {

    private String table;
    private Where where;

    From(String table) {
        this.table = table;
    }

    From(String table, Where where) {
        this.table = table;
        this.where = where;
    }

    @Override
    public List<String> interpret(Context ctx) {
        ctx.setTable(table);
        if (where == null) {
            return ctx.search();
        }
        return where.interpret(ctx);
    }
}
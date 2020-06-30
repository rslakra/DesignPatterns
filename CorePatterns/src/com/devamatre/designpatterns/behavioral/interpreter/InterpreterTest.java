package com.devamatre.designpatterns.behavioral.interpreter;

import java.util.List;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 8:03 PM
 * Version: 1.0.0
 */
public class InterpreterTest {

    public static void main(String[] args) {

        Expression query = new Select("name", new From("people"));
        Context ctx = new Context();
        List<String> result = query.interpret(ctx);
        System.out.println(result);

        Expression query2 = new Select("*", new From("people"));
        List<String> result2 = query2.interpret(ctx);
        System.out.println(result2);

        Expression query3 = new Select("name", new From("people", new Where(name -> name.toLowerCase().startsWith("d"))));
        List<String> result3 = query3.interpret(ctx);
        System.out.println(result3);
    }
}
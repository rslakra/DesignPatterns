package com.devamatre.designpatterns.behavioral.interpreter;

import java.util.List;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:47 PM
 * Version: 1.0.0
 */
public interface Expression {

    /**
     * @param context
     * @return
     */
    List<String> interpret(Context context);
}

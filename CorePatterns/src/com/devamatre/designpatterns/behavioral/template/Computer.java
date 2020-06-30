package com.devamatre.designpatterns.behavioral.template;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 9:10 PM
 * Version: 1.0.0
 */
public class Computer {

    private Map<String, String> computerParts = new HashMap<>();

    public Computer(Map<String, String> computerParts) {
        this.computerParts = computerParts;
    }

    public Map<String, String> getComputerParts() {
        return computerParts;
    }
}
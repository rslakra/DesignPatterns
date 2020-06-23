package com.devamatre.designpatterns.structural.facade;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:14 PM
 * Version: 1.0.0
 */
public class Shipping implements Department {
    /**
     *
     */
    @Override
    public void callHandler() {
        System.out.println("Shipping department welcomes you. How may I help you!");
    }
}

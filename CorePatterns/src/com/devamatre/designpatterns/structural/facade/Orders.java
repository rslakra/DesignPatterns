package com.devamatre.designpatterns.structural.facade;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:14 PM
 * Version: 1.0.0
 */
public class Orders implements Department {
    /**
     *
     */
    @Override
    public void callHandler() {
        System.out.println("Orders department welcomes you. How may I help you!");
    }
}

package com.devamatre.designpatterns.structural.proxy;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:28 PM
 * Version: 1.0.0
 */
public class RealInternet implements Internet {

    /**
     * @param host
     */
    @Override
    public void connect(String host) {
        System.out.println("Connecting to " + host);
    }
}

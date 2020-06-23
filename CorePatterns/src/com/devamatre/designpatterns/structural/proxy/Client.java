package com.devamatre.designpatterns.structural.proxy;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:32 PM
 * Version: 1.0.0
 */
public class Client {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();
        try {
            internet.connect("www.google.com");
            internet.connect("abc.com");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

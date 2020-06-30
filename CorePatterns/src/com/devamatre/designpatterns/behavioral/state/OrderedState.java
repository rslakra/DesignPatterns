package com.devamatre.designpatterns.behavioral.state;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 9:05 PM
 * Version: 1.0.0
 */
public class OrderedState implements PackageState {

    @Override
    public void next(Package pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public void prev(Package pkg) {
        System.out.println("The package is in it's root state.");
    }

    @Override
    public void printStatus() {
        System.out.println("Package ordered, not delivered to the office yet.");
    }

    @Override
    public String toString() {
        return "OrderedState{}";
    }
}
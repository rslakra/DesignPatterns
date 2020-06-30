package com.devamatre.designpatterns.behavioral.state;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 9:06 PM
 * Version: 1.0.0
 */
public class ReceivedState implements PackageState {

    @Override
    public void next(Package pkg) {
        System.out.println("This package is already received by a client.");
    }

    @Override
    public void prev(Package pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public void printStatus() {
        System.out.println("Package was received by client.");
    }

    @Override
    public String toString() {
        return "ReceivedState{}";
    }
}
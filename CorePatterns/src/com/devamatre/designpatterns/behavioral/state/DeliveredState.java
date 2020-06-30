package com.devamatre.designpatterns.behavioral.state;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 9:04 PM
 * Version: 1.0.0
 */
public class DeliveredState implements PackageState {

    @Override
    public void next(Package pkg) {
        pkg.setState(new ReceivedState());
    }

    @Override
    public void prev(Package pkg) {
        pkg.setState(new OrderedState());
    }

    @Override
    public void printStatus() {
        System.out.println("Package delivered to post office, not received yet.");
    }

    @Override
    public String toString() {
        return "DeliveredState{}";
    }

}
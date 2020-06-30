package com.devamatre.designpatterns.behavioral.state;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 9:05 PM
 * Version: 1.0.0
 */
public class Package {

    private PackageState state = new OrderedState();

    public PackageState getState() {
        return state;
    }

    public void setState(PackageState state) {
        this.state = state;
    }

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }

    public void printStatus() {
        state.printStatus();
    }
}
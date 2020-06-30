package com.devamatre.designpatterns.behavioral.state;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 9:04 PM
 * Version: 1.0.0
 */
public interface PackageState {

    void next(Package pkg);

    void prev(Package pkg);

    void printStatus();
}
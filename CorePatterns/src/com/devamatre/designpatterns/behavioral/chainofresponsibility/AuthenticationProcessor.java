package com.devamatre.designpatterns.behavioral.chainofresponsibility;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/22/20 7:48 PM
 * Version: 1.0.0
 */
public abstract class AuthenticationProcessor {

    // next element in chain or responsibility
    public AuthenticationProcessor nextProcessor;

    public AuthenticationProcessor(AuthenticationProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract boolean isAuthorized(AuthenticationProvider authProvider);
}
package com.oreilly.patterns.chapter9;

public class ServiceLocatorException extends Exception {
    public ServiceLocatorException(String s, Exception e) {
        super(s, e);
    }

}

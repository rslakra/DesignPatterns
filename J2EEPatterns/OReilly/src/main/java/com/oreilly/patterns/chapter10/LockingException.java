// LockingException required for compile;

package com.oreilly.patterns.chapter10;

public class LockingException extends Exception {
    public LockingException(String s) {
        super(s);
    }
}

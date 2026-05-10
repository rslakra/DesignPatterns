// InvalidPatientException is used by various samples.

package com.oreilly.patterns.chapter7;

public class InvalidPatientException extends Exception {
    public InvalidPatientException(String s)  {
        super(s);
    }
}

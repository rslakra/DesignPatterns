package com.rslakra.designpatterns.creational.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SingletonTest {

    @Test
    void getInstance_returnsSameReference() {
        Singleton a = Singleton.getInstance();
        Singleton b = Singleton.getInstance();
        assertSame(a, b);
    }

    @Test
    void setValue_and_getValue_roundTrip() {
        Singleton instance = Singleton.getInstance();
        instance.setValue(42);
        assertEquals(42, instance.getValue());
    }
}

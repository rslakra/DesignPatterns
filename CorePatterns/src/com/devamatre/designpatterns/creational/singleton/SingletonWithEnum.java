package com.devamatre.designpatterns.creational.singleton;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/21/20 1:40 PM
 * Version: 1.0.0
 */
public enum SingletonWithEnum {
    INSTANCE;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

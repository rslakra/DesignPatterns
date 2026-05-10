package com.devamatre.patterns.s2w.model;

/**
 * A (very) simple factory for generating new UserBeans
 */
public class UserBeanFactory {
    /**
     * Generate a new UserBeanImpl
     */
    public static UserBean newInstance() {
        return new UserBeanImpl();
    }
}

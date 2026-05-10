package com.rslakra.j2eepatterns.frontcontroller.model;

/**
 * A (very) simple factory to create UserBeanImpls
 */
public class UserBeanFactory {
	/**
	 * Generate a new instance of UserBeanImpl
	 */
	public static UserBean newInstance() {
		return new UserBeanImpl();
	}
}

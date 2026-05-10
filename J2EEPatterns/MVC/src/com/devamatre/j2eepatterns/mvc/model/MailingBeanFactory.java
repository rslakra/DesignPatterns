package com.devamatre.j2eepatterns.mvc.model;

/**
 * A (very) simple factory class to generate new MailingBeans
 */
public class MailingBeanFactory {
	/**
	 * Generate a new mailing bean.
	 */
	public static MailingBean newInstance() {
		return new MailingBeanImpl();
	}
}

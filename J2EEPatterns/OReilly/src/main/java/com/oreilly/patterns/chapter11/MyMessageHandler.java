// MyMessageHandler.java: inline example

package com.oreilly.patterns.chapter11;

import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;

class MyMessageHandler implements MessageListener {

	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			System.out.println("Message Received:" + msg.getText());
		} catch (JMSException e) {
			System.out.println(e.getMessage());
		}
	}
}

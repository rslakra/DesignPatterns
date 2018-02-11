/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.patterns.core.structural.bridge;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2007-01-04 03:36:37 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestBridge {

	public void fan() {
		new BridgeThread(new Fan()).run();
	}

	public void blub() {
		new BridgeThread(new Blub()).run();
	}

	public static void main(String[] args) {
		TestBridge t = new TestBridge();
		t.fan();
		t.blub();
	}

	private class BridgeThread implements Runnable {
		private Switch mSwitch;
		private volatile boolean active;

		public BridgeThread(Switch mSwitch) {
			this.mSwitch = mSwitch;
		}

		public void run() {
			System.out.println("Calling... " + Thread.currentThread());
			while (active) {
				try {
					mSwitch.on();
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					System.out.println("Interrupted..");
					active = false;
					mSwitch.off();
				}
			}
		}
	}
}

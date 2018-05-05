/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.designpatterns.creational.singleton;

import com.devamatre.designpatterns.creational.singleton.Singleton.LogType;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2005-04-25 05:56:03 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestSingleton {
	
	/**
	 * 
	 * @param arg
	 */
	public static void main(String arg[]) {
		String logFilePath = Singleton.getInstance().getLogFilePath();
		System.out.println("logFilePath:" + logFilePath);
		
		String logLevel = Singleton.getInstance().getLogLevel();
		System.out.println("logLevel:" + logLevel);
		
		Singleton.getInstance().logMsg(LogType.DEBUG, "Debug Message");
		Singleton.getInstance().logMsg(LogType.INFO, "Info Message");
		Singleton.getInstance().logMsg(LogType.WARN, "Warn Message");
		
		System.out.println("The output of instances:");
		
		System.out.println("SingletonPattern Instance:" + Singleton.getInstance());
		
		new Thread() {
			public void run() {
				System.out.println("First Instance: " + Singleton.getInstance());
			}
		}.start();
		
		new Thread() {
			public void run() {
				System.out.println("Second Instance: " + Singleton.getInstance());
			}
		}.start();
		
		new Thread() {
			public void run() {
				System.out.println("Third Instance: " + Singleton.getInstance());
			}
		}.start();
	}
}

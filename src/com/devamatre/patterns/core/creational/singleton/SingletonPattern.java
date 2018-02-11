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
package com.devamatre.patterns.core.creational.singleton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2007-01-04 03:36:37 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class SingletonPattern implements Singleton {

	private static SingletonPattern instance;

	private Properties properties;

	enum LogType {
		DEBUG, INFO, ERROR, WARN;
	}

	private SingletonPattern() {
	}

	/**
	 * 
	 * @return
	 */
	public static SingletonPattern getInstance() {
		if (instance == null) {
			synchronized (SingletonPattern.class) {
				if (instance == null) {
					instance = new SingletonPattern();
				}
			}
		}

		return instance;
	}

	/**
	 * Level of logging, error or information etc
	 *
	 * @return level, int
	 */
	public int getRegisteredLevel() {
		int i = 0;
		try {
			InputStream inputstream = getClass().getResourceAsStream("Logger.properties");
			properties.load(inputstream);
			inputstream.close();
			i = Integer.parseInt(properties.getProperty("logger.registeredlevel"));
			if (i < 0 || i > 3)
				i = 0;
		} catch (Exception exception) {
			System.out.println("Logger: Failed in the getRegisteredLevel method");
			exception.printStackTrace();
		}
		return i;
	}

	/**
	 * One file will be made daily. So, putting date time in file name.
	 *
	 * @param gc
	 *            GregorianCalendar
	 * @return String, name of file
	 */
	private String getFileName() {
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
		String dateString = dateFormat1.format(Calendar.getInstance().getTime());
		String logFilePath = getClass().getResource("Log-" + dateString + ".txt").getPath();
		return logFilePath;
	}

	/**
	 * A mechanism to log message to the file.
	 *
	 * @param logType
	 * @param message
	 *            String
	 */
	public void logMsg(LogType logType, String message) {
		try {
			String fileName = getFileName();
			FileOutputStream fos = new FileOutputStream(fileName, true);
			PrintStream ps = new PrintStream(fos);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm:ss a");
			ps.println("<" + dateFormat2.format(Calendar.getInstance().getTime()) + ">[" + message + "]");
			ps.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * 
	 * @param arg
	 */
	public static void main(String arg[]) {
		System.out.println("The output of two instance:");
		System.out.println("First Instance: " + SingletonPattern.getInstance());
		System.out.println("Second Instance:" + SingletonPattern.getInstance());
		System.out.println("Third Instance:" + SingletonPattern.getInstance());
		SingletonPattern.getInstance().logMsg(LogType.DEBUG, "Debug Message");
		SingletonPattern.getInstance().logMsg(LogType.INFO, "Debug Message");
		SingletonPattern.getInstance().logMsg(LogType.WARN, "Debug Message");
		String logFilePath = SingletonPattern.getInstance().getFileName();
		System.out.println("logFilePath:" + logFilePath);

	}
}

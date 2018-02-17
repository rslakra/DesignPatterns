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
package com.devamatre.designpatterns.creational.singleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 * This class returns the same instnace in the same JVM.
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2007-01-04 03:36:37 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class SingletonPattern {

	private final String fileName = "Logger.properties";
	private static SingletonPattern instance;
	private final SimpleDateFormat logDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm:ss a");
	private Properties properties;

	enum LogType {
		DEBUG, INFO, ERROR, WARN;
	}

	private SingletonPattern() {
	}

	/**
	 * Returns the singleton instance of this object.
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
	 * 
	 * @param inputstream
	 */
	public void safeClose(InputStream inputstream) {
		if (inputstream != null) {
			try {
				inputstream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	public void loadProperties(final String fileName) {
		InputStream inputstream = getClass().getResourceAsStream(fileName);
		if (inputstream != null) {
			try {
				if (properties == null) {
					properties = new Properties();
				}
				properties.load(inputstream);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		safeClose(inputstream);
	}

	/**
	 * Level of logging, error or information etc
	 *
	 * @return level, int
	 */
	public String getLogLevel() {
		String logLevel = null;
		if (properties == null) {
			loadProperties(fileName);
		}

		logLevel = properties.getProperty("logger.registeredlevel");
		return logLevel;
	}

	/**
	 * One file will be made daily. So, putting date time in file name.
	 *
	 * @param gc
	 *            GregorianCalendar
	 * @return String, name of file
	 */
	private String getLogFilePath() {
		String logFilePath = null;
		URL url = getClass().getResource("Logs.txt");
		if (url != null) {
			File file = new File(url.getPath().replaceAll("classes", "src"));
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

			if (file.exists()) {
				logFilePath = file.getAbsolutePath();
			}
		}

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
			String logFilePath = getLogFilePath();
			if (logFilePath != null) {
				PrintWriter output = new PrintWriter(new FileOutputStream(logFilePath, true), true);
				output.write("[");
				output.write(logDateFormat.format(Calendar.getInstance().getTime()));
				output.write("] [");
				output.write(message);
				output.write("]\n");
				output.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param arg
	 */
	public static void main(String arg[]) {
		String logFilePath = SingletonPattern.getInstance().getLogFilePath();
		System.out.println("logFilePath:" + logFilePath);

		String logLevel = SingletonPattern.getInstance().getLogLevel();
		System.out.println("logLevel:" + logLevel);

		SingletonPattern.getInstance().logMsg(LogType.DEBUG, "Debug Message");
		SingletonPattern.getInstance().logMsg(LogType.INFO, "Info Message");
		SingletonPattern.getInstance().logMsg(LogType.WARN, "Warn Message");

		System.out.println("The output of instances:");

		System.out.println("SingletonPattern Instance:" + SingletonPattern.getInstance());

		new Thread() {
			public void run() {
				System.out.println("First Instance: " + SingletonPattern.getInstance());
			}
		}.start();

		new Thread() {
			public void run() {
				System.out.println("Second Instance: " + SingletonPattern.getInstance());
			}
		}.start();

		new Thread() {
			public void run() {
				System.out.println("Third Instance: " + SingletonPattern.getInstance());
			}
		}.start();

	}
}

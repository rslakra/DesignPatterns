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
public class Singleton {

	private final String fileName = "Logger.properties";
	private static Singleton instance;
	private final SimpleDateFormat logDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm:ss a");
	private Properties properties;

	enum LogType {
		DEBUG, INFO, ERROR, WARN;
	}

	private Singleton() {
	}

	/**
	 * Returns the singleton instance of this object.
	 * 
	 * @return
	 */
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
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

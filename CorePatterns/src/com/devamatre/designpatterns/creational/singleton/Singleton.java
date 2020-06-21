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

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.PrintWriter;
import java.io.Serializable;
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
public final class Singleton implements Cloneable, Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private final String fileName = "dLogs.properties";
	private final SimpleDateFormat logDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm:ss a");
	
	// singleton
	private static Singleton instance;
	private Properties properties;
	private int value;
	
	enum LogType {
		DEBUG, INFO, ERROR, WARN;
	}
	
	/**
	 * 
	 */
	private Singleton() {
		if (null != instance) {
			throw new InstantiationError("Duplicate instance creation is not allowed!");
		}
	}
	
	/**
	 * Returns the singleton instance of this object.
	 * 
	 * @return
	 */
	public static Singleton getInstance() {
		if (instance == null) {
			// handle multi-threading
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
	 * @return
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * 
	 */
	public void printHashCode() {
		System.out.println(getClass().getSimpleName() + ", hashCode:" + this.hashCode());
	}
	
	/**
	 * To maintain the singleton guarantee, you must also override a
	 * <code>clone</code> method.
	 * 
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone is not allowed for this object!");
	}
	
	/**
	 * To maintain the singleton guarantee, you must also provide a
	 * <code>readResolve</code> method.
	 * 
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return instance;
	}
	
	// /**
	// * Avoid using custom classloaders - all singletons should be loaded by
	// * common parent classloader.
	// *
	// *
	// * @param classname
	// * @return
	// * @throws ClassNotFoundException
	// */
	// private static Class<?> getClass(String className) throws
	// ClassNotFoundException {
	// ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	// if(classLoader == null) {
	// classLoader = Singleton.class.getClassLoader();
	// }
	// return (classLoader.loadClass(className));
	// }
	
	/**
	 * Closes the <code>closeable</code> object.
	 * 
	 * @param closeable
	 */
	public void safeClose(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
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
	 * @return String, name of file
	 */
	public String getLogFilePath() {
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
}

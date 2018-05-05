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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Singleton Breaker.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 31, 2015 10:32:40 AM
 */
public class TestBreakSingleton {
	
	private static Singleton singleton = null;
	
	/**
	 * 
	 */
	public static void breakSingletonWithReflection() {
		System.out.println();
		
		Singleton instanceOne = Singleton.getInstance();
		instanceOne.printHashCode();
		System.out.println(instanceOne.hashCode());
		
		Singleton instanceTwo = null;
		try {
			Constructor<?>[] constructors = Singleton.class.getDeclaredConstructors();
			for (Constructor<?> constructor : constructors) {
				// should break the rule here.
				constructor.setAccessible(true);
				instanceTwo = (Singleton) constructor.newInstance();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		instanceTwo.printHashCode();
		System.out.println(instanceTwo.hashCode());
	}
	
	/**
	 * 
	 */
	public static void breakSingletonWithSerialization() {
		System.out.println();
		
		final String fileName = "singleton.ser";
		System.out.println("fileName:" + fileName);
		// getting singleton instance
		Singleton instanceOne = Singleton.getInstance();
		instanceOne.setValue(10);
		
		try {
			// Serialize to a file
			ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(fileName));
			objectOutput.writeObject(instanceOne);
			objectOutput.close();
			
			// now changed the value of singleton
			instanceOne.setValue(20);
			
			// Serialize to a file
			ObjectInput objectInput = new ObjectInputStream(new FileInputStream(fileName));
			Singleton instanceTwo = (Singleton) objectInput.readObject();
			objectInput.close();
			
			System.out.println("Instance One Value= " + instanceOne.getValue());
			System.out.println("Instance Two Value= " + instanceTwo.getValue());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public static void breakSingletonWithClone() {
		System.out.println();
		final String fileName = "singleton.ser";
		System.out.println("fileName:" + fileName);
		// getting singleton instance
		Singleton singleton = Singleton.getInstance();
		singleton.setValue(10);
		try {
			Singleton clonedObject = (Singleton) singleton.clone();
			System.out.println(clonedObject);
			System.out.println(clonedObject.hashCode());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @author Rohtash Singh (rohtash.singh@gmail.com)
	 * @version 1.0.0
	 * @since Jul 31, 2015 11:00:51 AM
	 */
	private static class SingletonRunnable implements Runnable {
		public void run() {
			Singleton threadSingleton = Singleton.getInstance();
			synchronized (SingletonRunnable.class) {
				if (singleton == null)
					singleton = threadSingleton;
			}
			
			System.out.println("Thread 1st Object Hash:" + singleton.hashCode());
			System.out.println("Thread 2nd Object Hash:" + threadSingleton.hashCode());
		}
	}
	
	/**
	 * 
	 * @throws InterruptedException
	 */
	public static void breakSingletonWithThreads() throws InterruptedException {
		System.out.println();
		
		// Both threads call Singleton.getInstance().
		Thread threadOne = new Thread(new SingletonRunnable());
		Thread threadTwo = new Thread(new SingletonRunnable());
		threadOne.start();
		threadTwo.start();
		threadOne.join();
		threadTwo.join();
	}
	
	/**
	 * 
	 */
	public static void breakSingletonWithClassLoads() {
		System.out.println();
		final String className = "com.rslakra.core.Singleton";
		String jarFilePath = System.getProperty("user.dir") + File.separator + "JavaExamples-v1.0.0.jar";
		System.out.println("jarFilePath:" + jarFilePath);
		
		// 1st class loader
		ClassLoader classLoader = null;
		Singleton objectSingleton = null;
		try {
			classLoader = new URLClassLoader(new URL[] { new File(jarFilePath).toURI().toURL() });
			System.out.println("classLoader:" + classLoader);
			Class<?> classObject = classLoader.loadClass(className);
			Method methodGetInstance = classObject.getDeclaredMethod("getInstance");
			objectSingleton = (Singleton) methodGetInstance.invoke(null);
			System.out.println("objectSingleton:" + objectSingleton);
			objectSingleton.printHashCode();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			classLoader = null;
			objectSingleton = null;
		}
		
		// 2nd class loader
		try {
			classLoader = new URLClassLoader(new URL[] { new File(jarFilePath).toURI().toURL() });
			System.out.println("classLoader:" + classLoader);
			Class<?> classObject = classLoader.loadClass(className);
			Method methodGetInstance = classObject.getDeclaredMethod("getInstance");
			objectSingleton = (Singleton) methodGetInstance.invoke(null);
			System.out.println("objectSingleton:" + objectSingleton);
			objectSingleton.printHashCode();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			classLoader = null;
			objectSingleton = null;
		}
		
		// 3rd class loader
		try {
			classLoader = new URLClassLoader(new URL[] { new File(jarFilePath).toURI().toURL() });
			System.out.println("classLoader:" + classLoader);
			Class<?> classObject = Class.forName(className, true, classLoader);
			Method methodGetInstance = classObject.getDeclaredMethod("getInstance");
			objectSingleton = (Singleton) methodGetInstance.invoke(null);
			System.out.println("objectSingleton:" + objectSingleton);
			objectSingleton.printHashCode();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			classLoader = null;
			objectSingleton = null;
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 1st try
		try {
			breakSingletonWithReflection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// 2nd try
		try {
			breakSingletonWithSerialization();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// 3rd try
		try {
			breakSingletonWithClone();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// 4th try
		try {
			breakSingletonWithThreads();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// 5th try
		try {
			breakSingletonWithClassLoads();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}

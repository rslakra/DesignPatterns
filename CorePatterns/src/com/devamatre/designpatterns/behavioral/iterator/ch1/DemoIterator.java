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
package com.devamatre.designpatterns.behavioral.iterator.ch1;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-16 02:09:34 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class DemoIterator {

	/**
	 * Just a quick one today: Pulling the proper documents directory on iOS has
	 * always been a pain and a bit of code that I always forget. Here’s a
	 * reminder for you and I.
	 * 
	 * Remember on iOS that we can only write to our application’s documents
	 * directory. We’re sandboxed out of most of the system and other
	 * applications to save us from each other and we can’t write into to the
	 * main bundle because that would defeat our code signature.
	 * 
	 * In Objective-C, something like this would get us the current documents
	 * directory:
	 * 
	 * @param list
	 */
	private static void fillList(ListArray<String> list) {
		list.add("Rohtash");
		list.add("Singh");
		list.add("Lakra");
		// list.add(1, "Just");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ListArray<String> list = new ListArray<String>();
		fillList(list);
		System.out.println(list);
		System.out.println(list.size());

		Iterator<String> itr = list.createInterator();
		while (itr.hasNext()) {
			System.out.println(itr.getNext());
		}

		System.out.println();

		itr = list.createInterator();
		while (itr.hasNext()) {
			System.out.println(itr.getNext());
		}

		list.clear();
		System.out.println(list.size());
		System.out.println();

		fillList(list);
		System.out.println(list.size());
		System.out.println();

		System.out.println(list.contains("Singh"));
		System.out.println(list.contains("R Singh"));
		System.out.println();

		System.out.println(list.remove("Singh"));
		System.out.println(list.remove("R Singh"));
		System.out.println();

		System.out.println(list);

	}

}

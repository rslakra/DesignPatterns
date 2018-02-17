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

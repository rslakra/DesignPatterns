/******************************************************************************
 * Copyright (C) Devamatre Technologies 2017
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
package com.devamatre.datastructure.lists;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-01-06 10:00:03 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class ListFactory {

	/**
	 * 
	 * @param listType
	 * @return
	 */
	public static <E> List<E> getListFor(ListType listType) {
		switch (listType) {
		case ARRAY_LIST:
			return new ArrayList<>();
		case LINKED_LIST:
			return new LinkedList<>();
		case DOUBLE_LINKED_LIST:
			return new DoubleLinkedList<>();

		default:
			return null;
		}
	}

	/**
	 * Fills the list.
	 * 
	 * @param list
	 */
	public static void fillList(List<String> list) {
		if (list != null) {
			list.add("Rohtash");
			list.add("Singh");
			list.add("Lakra");
			list.add("Just");
			list.add("today");
			list.add("bundle");
			list.add("directory");
			list.add("defeat");
			list.add("would");
			list.add("signature");
			list.add("other");
			list.add("code");
			list.add("because");
			list.add("each");
			list.add("applications");
			list.add("system");
			list.add("sandboxed");
			list.add("only");
			list.add("other");
			list.add("application");
			list.add("like");
			list.add("documents");
			list.add("current");
			list.add("would");
			list.add("this");
			list.add("directory");
			list.add("something");
			list.add("Objective-C");
			list.add("write");
			list.add("Remember");
			list.add("quick");
			list.add("been");
			list.add("for");
			list.add("and");
			list.add("forget");
			list.add("always");
			list.add("bit");
			list.add("pain");
			list.add("you");
			list.add("always");
			list.add("reminder");
			list.add("code");
			list.add("directory");
			list.add("iOS");
			list.add("the");
			list.add("documents");
			list.add("proper");
			list.add("Pulling");
			list.add("one");
			list.add("today");
			list.add("quick");
		}
	}

}

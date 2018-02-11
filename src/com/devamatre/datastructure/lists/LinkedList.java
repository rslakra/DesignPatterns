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
 * @created 2018-01-06 10:00:30 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class LinkedList<E> implements List<E> {

	/**
	 * 
	 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
	 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
	 * @created 2018-01-06 03:03:08 PM
	 * @version 1.0.0
	 * @since 1.0.0
	 */
	private class Node {
		private E data;
		private Node next;

		public Node(E data) {
			this.data = data;
			next = null;
		}

		/**
		 * Returns the value of data.
		 *
		 * @return the data
		 */
		public E getData() {
			return data;
		}

		/**
		 * Returns the value of next.
		 *
		 * @return the next
		 */
		public Node getNext() {
			return next;
		}

		/**
		 * The next to be set.
		 *
		 * @param next
		 *            the next to set
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		public String toString() {
			return (data != null ? data.toString() : "");
		}
	}

	private Node root;
	private int size;

	public LinkedList() {
		root = null;
		size = 0;
	}

	/**
	 * @return
	 * @see com.devamatre.algorithms.lists.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return
	 * @see com.devamatre.algorithms.lists.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * @param item
	 * @return
	 * @see com.devamatre.algorithms.lists.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object item) {
		return false;
	}

	/**
	 * 
	 * @param parent
	 * @param newNode
	 * @return
	 */
	private Node insert(Node parent, E data) {
		if (parent == null) {
			parent = new Node(data);
			size++;
			return parent;
		} else {
			return insert(parent.getNext(), data);
		}
	}

	/**
	 * @param item
	 * @return
	 * @see com.devamatre.algorithms.lists.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E item) {
		root = insert(root, item);
		return true;
	}

	/**
	 * @param o
	 * @return
	 * @see com.devamatre.algorithms.lists.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param items
	 * @return
	 * @see com.devamatre.algorithms.lists.List#containsAll(com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean containsAll(List<?> items) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param newItems
	 * @return
	 * @see com.devamatre.algorithms.lists.List#addAll(com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean addAll(List<? extends E> newItems) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param index
	 * @param newItems
	 * @return
	 * @see com.devamatre.algorithms.lists.List#addAll(int,
	 *      com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean addAll(int index, List<? extends E> newItems) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param items
	 * @return
	 * @see com.devamatre.algorithms.lists.List#removeAll(com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean removeAll(List<?> items) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param items
	 * @return
	 * @see com.devamatre.algorithms.lists.List#retainAll(com.devamatre.algorithms.lists.List)
	 */
	@Override
	public boolean retainAll(List<?> items) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @see com.devamatre.algorithms.lists.List#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param index
	 * @return
	 * @see com.devamatre.algorithms.lists.List#get(int)
	 */
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param index
	 * @param element
	 * @return
	 * @see com.devamatre.algorithms.lists.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param index
	 * @param element
	 * @see com.devamatre.algorithms.lists.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param index
	 * @return
	 * @see com.devamatre.algorithms.lists.List#remove(int)
	 */
	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param o
	 * @return
	 * @see com.devamatre.algorithms.lists.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param o
	 * @return
	 * @see com.devamatre.algorithms.lists.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @see com.devamatre.algorithms.lists.List#subList(int, int)
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the string representation of this object.
	 * 
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder("[");
		if (!isEmpty()) {
			Node temp = root;
			boolean isFirst = true;
			while (temp != null) {
				if (isFirst) {
					isFirst = false;
				}

				if (!isFirst) {
					sBuilder.append(", ");
				}

				sBuilder.append(temp.getData());
				temp = temp.getNext();
			}
		}

		return sBuilder.append("]").toString();
	}

}

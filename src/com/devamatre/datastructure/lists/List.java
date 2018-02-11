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
 * @created 2018-01-06 09:59:44 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public interface List<E> {

	/**
	 * Returns the number of elements in this list. If this list contains more
	 * than <tt>Integer.MAX_VALUE</tt> elements, returns
	 * <tt>Integer.MAX_VALUE</tt>.
	 *
	 * @return the number of elements in this list
	 */
	int size();

	/**
	 * Returns <tt>true</tt> if this list contains no elements.
	 *
	 * @return <tt>true</tt> if this list contains no elements
	 */
	boolean isEmpty();

	/**
	 * Returns <tt>true</tt> if this list contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this list contains at
	 * least one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o
	 *            element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 */
	boolean contains(Object item);

	// Modification Operations

	/**
	 * Appends the specified element to the end of this list (optional
	 * operation).
	 *
	 * <p>
	 * Lists that support this operation may place limitations on what elements
	 * may be added to this list. In particular, some lists will refuse to add
	 * null elements, and others will impose restrictions on the type of
	 * elements that may be added. List classes should clearly specify in their
	 * documentation any restrictions on what elements may be added.
	 *
	 * @param e
	 *            element to be appended to this list
	 * @return <tt>true</tt> (as specified by {@link List#add})
	 * @throws UnsupportedOperationException
	 *             if the <tt>add</tt> operation is not supported by this list
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             permit null elements
	 * @throws IllegalArgumentException
	 *             if some property of this element prevents it from being added
	 *             to this list
	 */
	boolean add(E e);

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present (optional operation). If this list does not contain the
	 * element, it is unchanged. More formally, removes the element with the
	 * lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
	 * (if such an element exists). Returns <tt>true</tt> if this list contained
	 * the specified element (or equivalently, if this list changed as a result
	 * of the call).
	 *
	 * @param o
	 *            element to be removed from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 * @throws ClassCastException
	 *             if the type of the specified element is incompatible with
	 *             this list (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             permit null elements (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 * @throws UnsupportedOperationException
	 *             if the <tt>remove</tt> operation is not supported by this
	 *             list
	 */
	boolean remove(Object o);

	// Bulk Modification Operations

	/**
	 * Returns <tt>true</tt> if this list contains all of the elements of the
	 * specified collection.
	 *
	 * @param c
	 *            collection to be checked for containment in this list
	 * @return <tt>true</tt> if this list contains all of the elements of the
	 *         specified collection
	 * @throws ClassCastException
	 *             if the types of one or more elements in the specified
	 *             collection are incompatible with this list (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if the specified collection contains one or more null
	 *             elements and this list does not permit null elements
	 *             (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>), or if
	 *             the specified collection is null
	 * @see #contains(Object)
	 */
	boolean containsAll(List<?> items);

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the specified
	 * collection's iterator (optional operation). The behavior of this
	 * operation is undefined if the specified collection is modified while the
	 * operation is in progress. (Note that this will occur if the specified
	 * collection is this list, and it's nonempty.)
	 *
	 * @param c
	 *            collection containing elements to be added to this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws UnsupportedOperationException
	 *             if the <tt>addAll</tt> operation is not supported by this
	 *             list
	 * @throws ClassCastException
	 *             if the class of an element of the specified collection
	 *             prevents it from being added to this list
	 * @throws NullPointerException
	 *             if the specified collection contains one or more null
	 *             elements and this list does not permit null elements, or if
	 *             the specified collection is null
	 * @throws IllegalArgumentException
	 *             if some property of an element of the specified collection
	 *             prevents it from being added to this list
	 * @see #add(Object)
	 */
	boolean addAll(List<? extends E> newItems);

	/**
	 * Inserts all of the elements in the specified collection into this list at
	 * the specified position (optional operation). Shifts the element currently
	 * at that position (if any) and any subsequent elements to the right
	 * (increases their indices). The new elements will appear in this list in
	 * the order that they are returned by the specified collection's iterator.
	 * The behavior of this operation is undefined if the specified collection
	 * is modified while the operation is in progress. (Note that this will
	 * occur if the specified collection is this list, and it's nonempty.)
	 *
	 * @param index
	 *            index at which to insert the first element from the specified
	 *            collection
	 * @param c
	 *            collection containing elements to be added to this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws UnsupportedOperationException
	 *             if the <tt>addAll</tt> operation is not supported by this
	 *             list
	 * @throws ClassCastException
	 *             if the class of an element of the specified collection
	 *             prevents it from being added to this list
	 * @throws NullPointerException
	 *             if the specified collection contains one or more null
	 *             elements and this list does not permit null elements, or if
	 *             the specified collection is null
	 * @throws IllegalArgumentException
	 *             if some property of an element of the specified collection
	 *             prevents it from being added to this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 *             (<tt>index &lt; 0 || index &gt; size()</tt>)
	 */
	boolean addAll(int index, List<? extends E> newItems);

	/**
	 * Removes from this list all of its elements that are contained in the
	 * specified collection (optional operation).
	 *
	 * @param c
	 *            collection containing elements to be removed from this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws UnsupportedOperationException
	 *             if the <tt>removeAll</tt> operation is not supported by this
	 *             list
	 * @throws ClassCastException
	 *             if the class of an element of this list is incompatible with
	 *             the specified collection (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if this list contains a null element and the specified
	 *             collection does not permit null elements (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>), or if
	 *             the specified collection is null
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	boolean removeAll(List<?> items);

	/**
	 * Retains only the elements in this list that are contained in the
	 * specified collection (optional operation). In other words, removes from
	 * this list all of its elements that are not contained in the specified
	 * collection.
	 *
	 * @param c
	 *            collection containing elements to be retained in this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws UnsupportedOperationException
	 *             if the <tt>retainAll</tt> operation is not supported by this
	 *             list
	 * @throws ClassCastException
	 *             if the class of an element of this list is incompatible with
	 *             the specified collection (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if this list contains a null element and the specified
	 *             collection does not permit null elements (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>), or if
	 *             the specified collection is null
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	boolean retainAll(List<?> items);

	/**
	 * Removes all of the elements from this list (optional operation). The list
	 * will be empty after this call returns.
	 *
	 * @throws UnsupportedOperationException
	 *             if the <tt>clear</tt> operation is not supported by this list
	 */
	void clear();

	// Comparison and hashing

	/**
	 * Compares the specified object with this list for equality. Returns
	 * <tt>true</tt> if and only if the specified object is also a list, both
	 * lists have the same size, and all corresponding pairs of elements in the
	 * two lists are <i>equal</i>. (Two elements <tt>e1</tt> and <tt>e2</tt> are
	 * <i>equal</i> if <tt>(e1==null ? e2==null :
	 * e1.equals(e2))</tt>.) In other words, two lists are defined to be equal
	 * if they contain the same elements in the same order. This definition
	 * ensures that the equals method works properly across different
	 * implementations of the <tt>List</tt> interface.
	 *
	 * @param o
	 *            the object to be compared for equality with this list
	 * @return <tt>true</tt> if the specified object is equal to this list
	 */
	boolean equals(Object o);

	/**
	 * Returns the hash code value for this list. The hash code of a list is
	 * defined to be the result of the following calculation:
	 * 
	 * <pre>
	 * {
	 * 	&#64;code
	 * 	int hashCode = 1;
	 * 	for (E e : list)
	 * 		hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
	 * }
	 * </pre>
	 * 
	 * This ensures that <tt>list1.equals(list2)</tt> implies that
	 * <tt>list1.hashCode()==list2.hashCode()</tt> for any two lists,
	 * <tt>list1</tt> and <tt>list2</tt>, as required by the general contract of
	 * {@link Object#hashCode}.
	 *
	 * @return the hash code value for this list
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	int hashCode();

	// Positional Access Operations

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 *             (<tt>index &lt; 0 || index &gt;= size()</tt>)
	 */
	E get(int index);

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element (optional operation).
	 *
	 * @param index
	 *            index of the element to replace
	 * @param element
	 *            element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws UnsupportedOperationException
	 *             if the <tt>set</tt> operation is not supported by this list
	 * @throws ClassCastException
	 *             if the class of the specified element prevents it from being
	 *             added to this list
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             permit null elements
	 * @throws IllegalArgumentException
	 *             if some property of the specified element prevents it from
	 *             being added to this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 *             (<tt>index &lt; 0 || index &gt;= size()</tt>)
	 */
	E set(int index, E element);

	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation). Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their
	 * indices).
	 *
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 * @throws UnsupportedOperationException
	 *             if the <tt>add</tt> operation is not supported by this list
	 * @throws ClassCastException
	 *             if the class of the specified element prevents it from being
	 *             added to this list
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             permit null elements
	 * @throws IllegalArgumentException
	 *             if some property of the specified element prevents it from
	 *             being added to this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 *             (<tt>index &lt; 0 || index &gt; size()</tt>)
	 */
	void add(int index, E element);

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one
	 * from their indices). Returns the element that was removed from the list.
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws UnsupportedOperationException
	 *             if the <tt>remove</tt> operation is not supported by this
	 *             list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 *             (<tt>index &lt; 0 || index &gt;= size()</tt>)
	 */
	E remove(int index);

	// Search Operations

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element. More
	 * formally, returns the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 *
	 * @param o
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 * @throws ClassCastException
	 *             if the type of the specified element is incompatible with
	 *             this list (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             permit null elements (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 */
	int indexOf(Object o);

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally,
	 * returns the highest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 *
	 * @param o
	 *            element to search for
	 * @return the index of the last occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 * @throws ClassCastException
	 *             if the type of the specified element is incompatible with
	 *             this list (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             permit null elements (<a href=
	 *             "Collection.html#optional-restrictions">optional</a>)
	 */
	int lastIndexOf(Object o);

	/**
	 * Returns a view of the portion of this list between the specified
	 * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive. (If
	 * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
	 * empty.) The returned list is backed by this list, so non-structural
	 * changes in the returned list are reflected in this list, and vice-versa.
	 * The returned list supports all of the optional list operations supported
	 * by this list.
	 * <p>
	 *
	 * This method eliminates the need for explicit range operations (of the
	 * sort that commonly exist for arrays). Any operation that expects a list
	 * can be used as a range operation by passing a subList view instead of a
	 * whole list. For example, the following idiom removes a range of elements
	 * from a list:
	 * 
	 * <pre>
	 * {@code
	 *      list.subList(from, to).clear();
	 * }
	 * </pre>
	 * 
	 * Similar idioms may be constructed for <tt>indexOf</tt> and
	 * <tt>lastIndexOf</tt>, and all of the algorithms in the
	 * <tt>Collections</tt> class can be applied to a subList.
	 * <p>
	 *
	 * The semantics of the list returned by this method become undefined if the
	 * backing list (i.e., this list) is <i>structurally modified</i> in any way
	 * other than via the returned list. (Structural modifications are those
	 * that change the size of this list, or otherwise perturb it in such a
	 * fashion that iterations in progress may yield incorrect results.)
	 *
	 * @param fromIndex
	 *            low endpoint (inclusive) of the subList
	 * @param toIndex
	 *            high endpoint (exclusive) of the subList
	 * @return a view of the specified range within this list
	 * @throws IndexOutOfBoundsException
	 *             for an illegal endpoint index value
	 *             (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
	 *         fromIndex &gt; toIndex</tt>)
	 */
	List<E> subList(int fromIndex, int toIndex);

}

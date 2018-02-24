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
package com.devamatre.designpatterns.structural.composite;

/**
 * 
 * @author Rohtash Singh Lakra
 */
// 1. "lowest common denominator"
interface Component {
	void traverse();
}

// 2. "Isa" relationship
class Primitive implements Component {
	private int value;
	
	public Primitive(int val) {
		value = val;
	}
	
	public void traverse() {
		System.out.print(value + "  ");
	}
}

// 2. "Isa" relationship
abstract class Composite implements Component {
	// 3. Couple to interface
	private Component[] children = new Component[9];
	private int total = 0;
	private int value;
	
	public Composite(int val) {
		value = val;
	}
	
	// 3. Couple to interface
	public void add(Component c) {
		children[total++] = c;
	}
	
	public void traverse() {
		System.out.print(value + "  ");
		for(int i = 0; i < total; i++) {
			// 4. Delegation and polymorphism
			children[i].traverse();
		}
	}
}

// Two different kinds of "container" classes. Most of the
// "meat" is in the Composite base class.
class Row extends Composite {
	public Row(int val) {
		super(val);
	}
	
	public void traverse() {
		System.out.print("Row");
		super.traverse();
	}
}

class Column extends Composite {
	public Column(int val) {
		super(val);
	}
	
	public void traverse() {
		System.out.print("Col");
		super.traverse();
	}
}

/**
 * Composite design pattern
 * 
 * Create a "lowest common denominator" that makes classes interchangeable
 * All concrete classes declare an "isa" relationship to the interface
 * All "container" classes couple themselves to the interface
 * "Container" classes use polymorphism as they delegate to their children
 *
 * @author Rohtash Singh Lakra
 * @date 08/29/2017 12:33:50 PM
 *
 */
public class CompositeExample {
	public static void main(String[] args) {
		Composite first = new Row(1);
		Composite second = new Column(2);
		Composite third = new Column(3);
		Composite fourth = new Row(4);
		Composite fifth = new Row(5);
		first.add(second);
		first.add(third);
		third.add(fourth);
		third.add(fifth);
		first.add(new Primitive(6));
		second.add(new Primitive(7));
		third.add(new Primitive(8));
		fourth.add(new Primitive(9));
		fifth.add(new Primitive(10));
		first.traverse();
	}
}

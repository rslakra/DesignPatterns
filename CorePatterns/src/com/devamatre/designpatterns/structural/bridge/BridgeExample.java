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
package com.devamatre.designpatterns.structural.bridge;

/**
 * Create an interface/wrapper class that "has a" implementation object and
 * delegates all requests to it
 * 
 * @author Rohtash Singh Lakra
 */
class Stack {
	protected IStack iStack;
	
	public Stack(String s) {
		if (s.equals("java")) {
			iStack = new MyStack();
		} else {
			iStack = new StackMine();
		}
	}
	
	public Stack() {
		this("java");
	}
	
	public void push(int in) {
		iStack.push(in);
	}
	
	public int pop() {
		return (Integer) iStack.pop();
	}
	
	public boolean isEmpty() {
		return iStack.empty();
	}
}

/**
 * Embellish the interface class with derived classes if desired
 */
class StackHanoi extends Stack {
	private int totalRejected = 0;
	
	public StackHanoi() {
		super("java");
	}
	
	public StackHanoi(String s) {
		super(s);
	}
	
	public int reportRejected() {
		return totalRejected;
	}
	
	public void push(int in) {
		if (!iStack.empty() && in > (Integer) iStack.peek()) {
			totalRejected++;
		} else {
			iStack.push(in);
		}
	}
}

/**
 * Create an implementation/body base class
 */
interface IStack {
	Object push(Object o);
	
	Object peek();
	
	boolean empty();
	
	Object pop();
}

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-05-12 04:04:30 PM
 * @version 1.0.0
 * @since 1.0.0
 */
final class MyStack extends java.util.Stack<Object> implements IStack {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
}

/**
 * Derive the separate implementations from the common abstraction
 */
class StackMine implements IStack {
	private Object[] items = new Object[20];
	private int total = -1;
	
	public Object push(Object o) {
		return items[++total] = o;
	}
	
	public Object peek() {
		return items[total];
	}
	
	public Object pop() {
		return items[total--];
	}
	
	public boolean empty() {
		return total == -1;
	}
}

/**
 * Bridge design pattern source code example
 * 
 * Create an implementation/body base class Derive the separate implementations
 * from the common abstraction Create an interface/wrapper class that "has a"
 * and delegates to the impl Embellish the interface class with derived classes
 * if desired
 *
 * @author Rohtash Singh Lakra
 * @date 08/29/2017 12:35:37 PM
 *
 */
public class BridgeExample {
	public static void main(String[] args) {
		Stack[] stacks = { new Stack("java"), new Stack("mine"), new StackHanoi("java"), new StackHanoi("mine") };
		for (int i = 0, num; i < 20; i++) {
			num = (int) (Math.random() * 1000) % 40;
			for (Stack stack : stacks) {
				stack.push(num);
			}
		}
		for (int i = 0; i < stacks.length; i++) {
			while (!stacks[i].isEmpty()) {
				System.out.print(stacks[i].pop() + "  ");
			}
			System.out.println();
		}
		System.out.println("total rejected is " + ((StackHanoi) stacks[3]).reportRejected());
		
	}
}

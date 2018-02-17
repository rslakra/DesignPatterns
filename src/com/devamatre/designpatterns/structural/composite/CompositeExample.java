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
 * @author Rohtash Singh Lakra (Rohtash.Lakra@nasdaq.com)
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

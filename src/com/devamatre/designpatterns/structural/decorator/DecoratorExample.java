package com.devamatre.designpatterns.structural.decorator;

/**
 * 
 * @author Rohtash Singh Lakra
 */
// 1. "lowest common denominator"
interface Widget {
	void draw();
}

// 3. "Core" class with "is a" relationship
class TextField implements Widget {
	private int width, height;
	
	public TextField(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void draw() {
		System.out.println("TextField: " + width + ", " + height);
	}
}

// 2. Second level base class with "isa" relationship
abstract class Decorator implements Widget {
	// 4. "has a" relationship
	private Widget widget;
	
	public Decorator(Widget widget) {
		this.widget = widget;
	}
	
	// 5. Delegation
	public void draw() {
		widget.draw();
	}
}

// 6. Optional embellishment
class BorderDecorator extends Decorator {
	public BorderDecorator(Widget widget) {
		super(widget);
	}
	
	public void draw() {
		// 7. Delegate to base class and add extra stuff
		super.draw();
		System.out.println("  BorderDecorator");
	}
}

// 6. Optional embellishment
class ScrollDecorator extends Decorator {
	public ScrollDecorator(Widget widget) {
		super(widget);
	}
	
	public void draw() {
		super.draw(); // 7. Delegate to base class and add extra stuff
		System.out.println("  ScrollDecorator");
	}
}

/**
 * Decorator design pattern
 * 
 * Create a "lowest common denominator" that makes classes interchangeable
 * Create a second level base class for optional functionality
 * "Core" class and "Decorator" class declare an "isa" relationship
 * Decorator class "has a" instance of the "lowest common denominator"
 * Decorator class delegates to the "has a" object
 * Create a Decorator derived class for each optional embellishment
 * Decorator derived classes delegate to base class AND add extra stuff
 * Client has the responsibility to compose desired configurations
 *
 * @author Rohtash Singh Lakra (Rohtash.Lakra@nasdaq.com)
 * @date 08/29/2017 12:37:52 PM
 *
 */
public class DecoratorExample {
	
	public static void main(String[] args) {
		// 8. Client has the responsibility to compose desired configurations
		Widget widget = new BorderDecorator(new BorderDecorator(new ScrollDecorator(new TextField(80, 24))));
		widget.draw();
	}
	
}

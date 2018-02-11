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
package com.devamatre.patterns.core.creational.abstractfactory.ch2;

public class TestAbstractFactory {
	public static void main(String[] args) {
		// get shape factory
		AbstractFactory shapeFactory = FactoryProducer
				.getFactory(FactoryType.SHAPE);
		// get an object of Shape Circle
		Shape shape = shapeFactory.getShape(ShapeType.CIRCLE);
		// call draw method of Shape Circle 
		shape.draw();
		// get an object of Shape Rectangle
		shape = shapeFactory.getShape(ShapeType.RECTANGLE);
		// call draw method of Shape Rectangle
		shape.draw();
		// get an object of Shape Square
		shape = shapeFactory.getShape(ShapeType.SQUARE);
		// call draw method of Shape Square
		shape.draw();

		// get color factory
		AbstractFactory colorFactory = FactoryProducer
				.getFactory(FactoryType.COLOR);
		// get an object of Color Red
		Color color = colorFactory.getColor(ColorType.RED);
		// call fill method of Red
		color.fill();
		// get an object of Color Green
		color = colorFactory.getColor(ColorType.GREEN);
		// call fill method of Green
		color.fill();
		// get an object of Color Blue
		color = colorFactory.getColor(ColorType.BLUE);
		// call fill method of Color Blue
		color.fill();
	}
}
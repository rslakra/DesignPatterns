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
package com.devamatre.patterns.core.creational.factory.ch2;

/**
 * The <code>TestFactory</code>
 * 
 * @author Rohtash Singh
 * @created: Jun 13, 2013
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestFactory {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// get an object of Circle and call its draw method.
		Shape shape = ShapeFactory.createShape(ShapeType.CIRCLE);
		// call draw method of Circle
		shape.draw();

		// get an object of Rectangle and call its draw method.
		shape = ShapeFactory.createShape(ShapeType.RECTANGLE);
		// call draw method of Rectangle
		shape.draw();

		// get an object of Square and call its draw method.
		shape = ShapeFactory.createShape(ShapeType.SQUARE);
		// call draw method of circle
		shape.draw();
	}
}

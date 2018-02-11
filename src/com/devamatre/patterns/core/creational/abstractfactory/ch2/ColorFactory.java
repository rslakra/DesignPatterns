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

import com.devamatre.patterns.core.creational.abstractfactory.ch2.colors.Blue;
import com.devamatre.patterns.core.creational.abstractfactory.ch2.colors.Green;
import com.devamatre.patterns.core.creational.abstractfactory.ch2.colors.Red;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2013-06-13 03:14:35 PM
 * @version 1.0.0
 * @since 1.0.0
 */
final public class ColorFactory extends AbstractFactory {

	/**
	 * Returns the object for the specified color.
	 * 
	 * @param colorType
	 * @return
	 * @see com.devamatre.examples.designpatterns.core.creational.abstractfactory.ch2.AbstractFactory#getColor(com.devamatre.examples.designpatterns.core.creational.abstractfactory.ch2.ColorType)
	 */
	@Override
	Color getColor(ColorType colorType) {
		Color color = null;
		/* validate the specified type is not null or empty. */
		if (colorType != null) {
			switch (colorType) {
			case RED:
				color = new Red();
				break;
			case GREEN:
				color = new Green();
				break;
			case BLUE:
				color = new Blue();
				break;
			default:
				System.out.println("Invalid Color Type:" + colorType.toString());
				break;
			}
		}
		return color;
	}

	/**
	 * Returns the object for the specified shape.
	 * 
	 * @param shapeType
	 * @return
	 * @see com.devamatre.examples.designpatterns.core.creational.abstractfactory.ch2.AbstractFactory#getShape(com.devamatre.examples.designpatterns.core.creational.abstractfactory.ch2.ShapeType)
	 */
	@Override
	Shape getShape(ShapeType shapeType) {
		return null;
	}
}
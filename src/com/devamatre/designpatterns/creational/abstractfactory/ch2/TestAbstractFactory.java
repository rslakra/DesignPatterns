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
package com.devamatre.designpatterns.creational.abstractfactory.ch2;

import com.devamatre.designpatterns.bos.Color;
import com.devamatre.designpatterns.bos.Shape;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2018-02-17 10:40:17 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestAbstractFactory {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// get shape factory
		AbstractFactory shapeFactory = FactoryProducer.getFactory(FactoryType.SHAPE);
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
		AbstractFactory colorFactory = FactoryProducer.getFactory(FactoryType.COLOR);
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
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
package com.devamatre.patterns.core.creational.builder.ch1;

/**
 * The <code>VegBurger</code>
 * 
 * @author Rohtash Singh
 * @created: Jun 13, 2013
 * @version 1.0.0
 * @since 1.0.0
 */
public class VegBurger extends Burger {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.devamatre.examples.designpatterns.core.creational.builder.ch1.Item#price()
	 */
	@Override
	public float price() {
		return 25.0f;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.devamatre.examples.designpatterns.core.creational.builder.ch1.Item#name()
	 */
	@Override
	public String name() {
		return "Veg Burger";
	}
}
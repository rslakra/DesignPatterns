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
package com.devamatre.patterns.core.creational.prototype;

/**
 * TODO :
 * Date :Jan 4, 2007 9:05:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Prototype implements Cloneable {
    private int value;

    public Prototype(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        }catch(CloneNotSupportedException ex) {
            System.out.println("Error while making clone, ex : " + ex);
        }
        return clone;
    }

    /**
     * Starting Point to Test.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Prototype pType = new Prototype(10);
        System.out.println("Value : " + pType.getValue());
        Prototype clone = (Prototype)pType.clone();
        System.out.println("Clone Value : " + clone.getValue());
    }
}

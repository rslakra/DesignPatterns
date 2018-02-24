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
package com.devamatre.designpatterns.structural.adapter;

/* The OLD */
class SquarePeg {
	private double width;

	public SquarePeg(double width) {
		this.width = width;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
}

/* The NEW */
class RoundHole {
	private final int radius;

	public RoundHole(int radius) {
		this.radius = radius;
		System.out.println("RoundHole: max SquarePeg is " + radius * Math.sqrt(2));
	}

	public int getRadius() {
		return radius;
	}
}

// Design a "wrapper" class that can "impedance match" the old to the new
class SquarePegAdapter {
	// The adapter/wrapper class "has a" instance of the legacy class
	private final SquarePeg squarePeg;

	public SquarePegAdapter(double w) {
		squarePeg = new SquarePeg(w);
	}

	// Identify the desired interface
	public void makeFit(RoundHole roundHole) {
		// The adapter/wrapper class delegates to the legacy object
		double amount = squarePeg.getWidth() - roundHole.getRadius() * Math.sqrt(2);
		System.out.println(
				"reducing SquarePeg " + squarePeg.getWidth() + " by " + ((amount < 0) ? 0 : amount) + " amount");
		if (amount > 0) {
			squarePeg.setWidth(squarePeg.getWidth() - amount);
			System.out.println("   width is now " + squarePeg.getWidth());
		}
	}
}

/**
 * Adapter source code example
 * 
 * Identify the desired interface. Design a "wrapper" class that can "impedance
 * match" the old to the new. The adapter/wrapper class "has a" instance of the
 * legacy class. The adapter/wrapper class "maps" (or delegates) to the legacy
 * object. The client uses (is coupled to) the new interface.
 *
 * @author Rohtash Singh Lakra
 * @date 08/29/2017 12:36:32 PM
 */
public class AdapterExample {

	public static void main(String[] args) {
		RoundHole roundHole = new RoundHole(5);
		SquarePegAdapter squarePegAdapter;
		for (int i = 6; i < 10; i++) {
			squarePegAdapter = new SquarePegAdapter((double) i);
			// The client uses (is coupled to) the new interface
			squarePegAdapter.makeFit(roundHole);
		}
	}

}

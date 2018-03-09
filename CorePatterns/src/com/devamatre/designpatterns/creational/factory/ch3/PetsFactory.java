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
package com.devamatre.designpatterns.creational.factory.ch3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2000-02-17 10:52:08 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class PetsFactory {

	List<Pet> pets = new ArrayList<Pet>();

	public PetsFactory() {
		pets.add(new Dog("Gus"));
		pets.add(new Dog("Trapper"));
		pets.add(new Dog("Bailey"));
		pets.add(new Dog("Cooper"));
		pets.add(new Dog("Hawkeye"));
		pets.add(new Dog("Boomer"));
		pets.add(new Dog("Lucky"));

		pets.add(new Puppy("Joey"));
		pets.add(new Puppy("Stanley"));
		pets.add(new Puppy("Rober"));
		pets.add(new Puppy("Ozzy"));
		pets.add(new Puppy("Skits"));
	}

	public void addPet(Pet pet) {
		pets.add(pet);
	}

	public <T> List<? extends Pet> getPets(String type) {
		List<Pet> oPets = new ArrayList<Pet>();

		if ("Dog".equals(type)) {
			for (Pet pet : pets) {
				if ("Dog".equals(pet.getType())) {
					oPets.add(pet);
				}
			}
		} else {
			for (Pet pet : pets) {
				if ("Puppy".equals(pet.getType())) {
					oPets.add((Puppy) pet);
				}
			}
		}

		return oPets;
	}

	public void show(List<? extends Pet> pets) {
		System.out.println(pets);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PetsFactory petsFactory = new PetsFactory();
		List<? extends Pet> pets = petsFactory.getPets("Dog");
		petsFactory.show(pets);
		pets = petsFactory.getPets("Puppy");
		petsFactory.show(pets);
	}

}
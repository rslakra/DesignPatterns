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
package com.devamatre.designpatterns;

import java.util.ArrayList;
import java.util.List;

class Pet {
	String name;
	String type;

	public Pet(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof Pet) {
			Pet oPet = (Pet) obj;
			return getName().equals(oPet.getName()) && getType().equals(oPet.getType());
		}

		return false;
	}

	public int hashCode() {
		return getName().hashCode();
	}

	public String toString() {
		return getName();
	}
}

class Dog extends Pet {

	public Dog(String name) {
		super(name, "Dog");
	}
}

class Puppy extends Pet {
	public Puppy(String name) {
		super(name, "Puppy");
	}
}

class PetsFactory {

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

}

public class AnimalFactory {

	public static void main(String[] args) {
		PetsFactory petsFactory = new PetsFactory();
		List<? extends Pet> pets = petsFactory.getPets("Dog");
		petsFactory.show(pets);
		pets = petsFactory.getPets("Puppy");
		petsFactory.show(pets);
	}
}

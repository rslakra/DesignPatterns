package com.devamatre.designpatterns.behavioral.template;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 9:09 PM
 * Version: 1.0.0
 */
public class TemplateTest {
    public static void main(String[] args) {
        ComputerBuilder standardComputerBuilder = new StandardComputerBuilder();
        Computer standardComputer = standardComputerBuilder.buildComputer();
        standardComputer.getComputerParts().forEach((k, v) -> System.out.println("Part : " + k + " Value : " + v));

        ComputerBuilder highEndComputerBuilder = new HighEndComputerBuilder();
        Computer highEndComputer = highEndComputerBuilder.buildComputer();
        highEndComputer.getComputerParts().forEach((k, v) -> System.out.println("Part : " + k + " Value : " + v));
    }
}

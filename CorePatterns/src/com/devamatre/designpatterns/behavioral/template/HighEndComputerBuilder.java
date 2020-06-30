package com.devamatre.designpatterns.behavioral.template;

/**
 * Author: Rohtash Singh Lakra
 * Created: 6/29/20 9:12 PM
 * Version: 1.0.0
 */
public class HighEndComputerBuilder extends ComputerBuilder {

    @Override
    public void addMotherboard() {
        computerParts.put("Motherboard", "High-end Motherboard");
    }

    @Override
    public void setupMotherboard() {
        motherboardSetupStatus.add("Screwing the high-end motherboard to the case.");
        motherboardSetupStatus.add("Pluging in the power supply connectors.");
        motherboardSetupStatus.forEach(step -> System.out.println(step));
    }

    @Override
    public void addProcessor() {
        computerParts.put("Processor", "High-end Processor");
    }
}
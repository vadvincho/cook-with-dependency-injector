package com.vadzimvincho.cook.vegetables.vegetablefactory;

import com.vadzimvincho.cook.model.vegetable.Cucumber;
import com.vadzimvincho.cook.model.vegetable.Vegetable;
import com.vadzimvincho.cook.vegetables.vegetablesupplier.VegetableSupplier;
import com.vadzimvincho.dependencyinjector.annotations.DependencyInjectionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@DependencyInjectionObject
public class CucumberFactory implements VegetableFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberFactory.class);

    @Override
    public List<Vegetable> getVegetables() {
        List<Vegetable> vegetables = new ArrayList<>();
        for (List<String> line : VegetableSupplier.getVegetablesFromFile("cucumber")) {
            Cucumber cucumber = new Cucumber();
            cucumber.setName(line.get(0));
            cucumber.setThornsColor(line.get(1));
            cucumber.setShirt(line.get(2));
            cucumber.setCalories(Integer.parseInt(line.get(3)));
            cucumber.setWeight(Integer.parseInt(line.get(4)));
            vegetables.add(cucumber);
        }
        LOGGER.debug("List of cucumber from cucumber factory obtained [{}]", vegetables);
        return vegetables;
    }

    @Override
    public String toString() {
        return "CucumberFactory";
    }
}
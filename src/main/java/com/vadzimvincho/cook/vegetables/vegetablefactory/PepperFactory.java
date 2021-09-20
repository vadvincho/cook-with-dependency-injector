package com.vadzimvincho.cook.vegetables.vegetablefactory;

import com.vadzimvincho.cook.model.vegetable.Pepper;
import com.vadzimvincho.cook.model.vegetable.Vegetable;
import com.vadzimvincho.cook.vegetables.vegetablesupplier.VegetableSupplier;
import com.vadzimvincho.dependencyinjector.annotations.DependencyInjectionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@DependencyInjectionObject
public class PepperFactory implements VegetableFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(PepperFactory.class);

    @Override
    public List<Vegetable> getVegetables() {
        List<Vegetable> vegetables = new ArrayList<>();
        for (List<String> line : VegetableSupplier.getVegetablesFromFile("pepper")) {
            Pepper pepper = new Pepper();
            pepper.setName(line.get(0));
            pepper.setGenus(line.get(1));
            pepper.setPepperColor(line.get(2));
            pepper.setCalories(Integer.parseInt(line.get(3)));
            pepper.setWeight(Integer.parseInt(line.get(4)));
            vegetables.add(pepper);
        }
        LOGGER.debug("List of pepper from pepper factory obtained [{}]", vegetables);
        return vegetables;
    }

    @Override
    public String toString() {
        return "PepperFactory";
    }
}

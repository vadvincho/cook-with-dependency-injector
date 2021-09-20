package com.vadzimvincho.cook.vegetables.vegetablefactory;

import com.vadzimvincho.cook.model.vegetable.Tomato;
import com.vadzimvincho.cook.model.vegetable.Vegetable;
import com.vadzimvincho.cook.vegetables.vegetablesupplier.VegetableSupplier;
import com.vadzimvincho.dependencyinjector.annotations.DependencyInjectionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@DependencyInjectionObject
public class TomatoFactory implements VegetableFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(TomatoFactory.class);

    @Override
    public List<Vegetable> getVegetables() {
        List<Vegetable> vegetables = new ArrayList<>();
        for (List<String> line : VegetableSupplier.getVegetablesFromFile("tomato")) {
            Tomato tomato = new Tomato();
            tomato.setName(line.get(0));
            tomato.setTomatoColor(line.get(1));
            tomato.setTomatoShape(line.get(2));
            tomato.setCalories(Integer.parseInt(line.get(3)));
            tomato.setWeight(Integer.parseInt(line.get(4)));
            vegetables.add(tomato);
        }
        LOGGER.debug("List of tomato from tomato factory obtained [{}]", vegetables);
        return vegetables;
    }

    @Override
    public String toString() {
        return "TomatoFactory";
    }
}
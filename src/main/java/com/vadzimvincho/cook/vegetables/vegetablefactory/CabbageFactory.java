package com.vadzimvincho.cook.vegetables.vegetablefactory;

import com.vadzimvincho.cook.model.vegetable.Cabbage;
import com.vadzimvincho.cook.model.vegetable.Vegetable;
import com.vadzimvincho.cook.vegetables.vegetablestorage.RestaurantVegetableStorage;
import com.vadzimvincho.cook.vegetables.vegetablesupplier.VegetableSupplier;
import com.vadzimvincho.dependencyinjector.annotations.DependencyInjectionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@DependencyInjectionObject
public class CabbageFactory implements VegetableFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CabbageFactory.class);

    @Override
    public List<Vegetable> getVegetables() {
        List<Vegetable> vegetables = new ArrayList<>();
        for (List<String> line : VegetableSupplier.getVegetablesFromFile("cabbage")) {
            Cabbage cabbage = new Cabbage();
            cabbage.setName(line.get(0));
            cabbage.setType(line.get(1));
            cabbage.setColorCabbage(line.get(2));
            cabbage.setCalories(Integer.parseInt(line.get(3)));
            cabbage.setWeight(Integer.parseInt(line.get(4)));
            vegetables.add(cabbage);
        }
        LOGGER.debug("List of cabbage from cabbage factory obtained [{}]", vegetables);
        return vegetables;
    }

    @Override
    public String toString() {
        return "CabbageFactory";
    }
}
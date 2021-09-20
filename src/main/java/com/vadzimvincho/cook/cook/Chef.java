package com.vadzimvincho.cook.cook;

import com.vadzimvincho.cook.model.recipe.Recipe;
import com.vadzimvincho.cook.model.salad.Salad;
import com.vadzimvincho.cook.model.vegetable.Vegetable;
import com.vadzimvincho.cook.util.VegetableComparator;
import com.vadzimvincho.cook.vegetables.vegetablestorage.VegetableStorage;
import com.vadzimvincho.dependencyinjector.annotations.DependencyInjectionObject;
import com.vadzimvincho.dependencyinjector.annotations.InjectField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@DependencyInjectionObject
public class Chef implements Cook {
    private static final Logger LOGGER = LoggerFactory.getLogger(Chef.class);

    @InjectField
    private VegetableStorage vegetableStorage;

    public void setVegetableStorage(VegetableStorage vegetableStorage) {
        this.vegetableStorage = vegetableStorage;
    }

    @Override
    public List<Recipe> getMenu() {
        return Arrays.asList(Recipe.values());
    }

    @Override
    public Salad cookSaladFromMenu(Recipe recipe) {
        Salad salad = new Salad(recipe.name());
        try {
            salad.setVegetables(vegetableStorage.getVegetablesByRecipe(recipe));
            LOGGER.info("Salad is ready. {}", salad);
        } catch (NoSuchElementException e) {
            LOGGER.info("Salad can't be prepared with this recipe. You must choose another. " + e.getMessage());
        }
        return salad;
    }

    @Override
    public Salad cookSaladByChef(int amount) {
        Salad salad = new Salad("SALAD_BY_CHEF");
        salad.setVegetables(vegetableStorage.getVegetablesRandom(amount));
        LOGGER.info("Salad {} is ready", salad.getName());
        return salad;
    }

    public int getCalories(Salad salad) {
        int calories = 0;
        for (Vegetable vegetable : salad.getVegetables()) {
            calories += vegetable.getCalories() * vegetable.getWeight() / 100;
        }
        LOGGER.info("Salad contains {} calories", calories);
        return calories;
    }

    public List<Vegetable> getVegetableByCalories(Salad salad, int minCalories, int maxCalories) {
        List<Vegetable> vegetables = new ArrayList<>();
        for (Vegetable vegetable : salad.getVegetables()) {
            int calories = vegetable.getCalories();
            if (calories >= minCalories && calories <= maxCalories) {
                vegetables.add(vegetable);
            }
        }
        LOGGER.info("Salad includes vegetables that contain {} to {} calories: {}", minCalories, maxCalories, vegetables);
        return vegetables;
    }

    public List<Vegetable> sortByName(Salad salad) {
        return sort(salad, VegetableComparator.SORT_BY_NAME);
    }

    public List<Vegetable> sortByCalories(Salad salad) {
        return sort(salad, VegetableComparator.SORT_BY_CALORIES);

    }

    private List<Vegetable> sort(Salad salad, VegetableComparator vegetableComparator) {
        List<Vegetable> sortList = vegetableComparator.sort(salad.getVegetables());
        LOGGER.info("Sorted by [{}] list of vegetables of salad [{}]: [{}]", vegetableComparator.name(), salad, sortList);
        return sortList;
    }

    @Override
    public String toString() {
        return "Chef{ " +
                "restaurantVegetableStorage=" + vegetableStorage +
                '}';
    }
}
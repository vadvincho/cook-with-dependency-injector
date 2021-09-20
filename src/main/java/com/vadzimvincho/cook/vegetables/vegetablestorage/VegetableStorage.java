package com.vadzimvincho.cook.vegetables.vegetablestorage;

import com.vadzimvincho.cook.model.recipe.Recipe;
import com.vadzimvincho.cook.model.vegetable.Vegetable;

import java.util.List;

public interface VegetableStorage {
    List<Vegetable> getVegetablesByRecipe(Recipe recipe);

    List<Vegetable> getVegetablesRandom(int amount);
}

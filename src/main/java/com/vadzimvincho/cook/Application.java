package com.vadzimvincho.cook;

import com.vadzimvincho.cook.cook.Cook;
import com.vadzimvincho.cook.model.recipe.Recipe;
import com.vadzimvincho.cook.model.salad.Salad;
import com.vadzimvincho.dependencyinjector.ApplicationObjectFactory;
import com.vadzimvincho.dependencyinjector.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        ObjectFactory objectFactory = new ApplicationObjectFactory("com.vadzimvincho");
        System.out.println(objectFactory.getObject(Cook.class));

        Cook chef = (Cook) objectFactory.getObject(Cook.class);
        System.out.println(chef.getMenu());

        Salad salad = chef.cookSaladFromMenu(Recipe.DIETARY_SALAD);
        chef.getCalories(salad);
        chef.getVegetableByCalories(salad, 50, 120);
        chef.sortByCalories(salad);
        chef.sortByName(salad);
        Salad salad2 = chef.cookSaladFromMenu(Recipe.PARMIGIANA);
    }
}

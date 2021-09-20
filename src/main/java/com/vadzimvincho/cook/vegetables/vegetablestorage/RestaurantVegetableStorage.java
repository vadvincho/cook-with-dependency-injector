package com.vadzimvincho.cook.vegetables.vegetablestorage;

import com.vadzimvincho.cook.model.recipe.Recipe;
import com.vadzimvincho.cook.model.vegetable.Vegetable;
import com.vadzimvincho.cook.vegetables.vegetablefactory.VegetableFactory;
import com.vadzimvincho.dependencyinjector.annotations.DependencyInjectionObject;
import com.vadzimvincho.dependencyinjector.annotations.InitObject;
import com.vadzimvincho.dependencyinjector.annotations.InjectField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@DependencyInjectionObject
public class RestaurantVegetableStorage implements VegetableStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantVegetableStorage.class);

    private final List<Vegetable> vegetableStorage = new ArrayList<>();

    @InjectField(name = "TomatoFactory")
    private VegetableFactory tomatoFactory;
    @InjectField(name = "CucumberFactory")
    private VegetableFactory cucumberFactory;
    @InjectField(name = "PepperFactory")
    private VegetableFactory pepperFactory;
    @InjectField(name = "CabbageFactory")
    private VegetableFactory cabbageFactory;

    public void setTomatoFactory(VegetableFactory tomatoFactory) {
        this.tomatoFactory = tomatoFactory;
    }

    public void setCucumberFactory(VegetableFactory cucumberFactory) {
        this.cucumberFactory = cucumberFactory;
    }

    public void setPepperFactory(VegetableFactory pepperFactory) {
        this.pepperFactory = pepperFactory;
    }

    public void setCabbageFactory(VegetableFactory cabbageFactory) {
        this.cabbageFactory = cabbageFactory;
    }

    @InitObject
    private void initVegetableStorage() {
        vegetableStorage.addAll(cucumberFactory.getVegetables());
        vegetableStorage.addAll(cabbageFactory.getVegetables());
        vegetableStorage.addAll(pepperFactory.getVegetables());
        vegetableStorage.addAll(tomatoFactory.getVegetables());
        LOGGER.debug("Vegetable storage initialization was successful");
    }

    @Override
    public List<Vegetable> getVegetablesByRecipe(Recipe recipe) {
        List<Vegetable> vegetables = new ArrayList<>();
        List<String> recipeList = recipe.getRecipe();
        Vegetable vegetableByRecipe = null;
        for (String vegetableName : recipeList) {
            vegetableByRecipe = getVegetable(vegetableName);
            if (vegetableByRecipe == null) {
                LOGGER.debug("No such vegetable in vegetable storage. Vegetable: " + vegetableName);
                initVegetableStorage();
                vegetableByRecipe = getVegetable(vegetableName);
                if (vegetableByRecipe == null) {
                    throw new NoSuchElementException("No such vegetable in vegetable factory. Vegetable: " + vegetableName);
                }
            }
            vegetableStorage.remove(vegetableByRecipe);
            vegetables.add(vegetableByRecipe);
        }
        LOGGER.debug("List of vegetables compiled according by recipe [{}]", recipe);
        return vegetables;
    }

    @Override
    public List<Vegetable> getVegetablesRandom(int amount) {
        Random random = new Random();
        List<Vegetable> vegetables = IntStream
                .generate(() -> random.nextInt(vegetableStorage.size()))
                .distinct()
                .limit(amount)
                .mapToObj(index -> vegetableStorage.get(index))
                .collect(Collectors.toList());
        LOGGER.debug("List of vegetables compiled according by random");
        return vegetables;
    }

    private Vegetable getVegetable(String vegetableName) {
        Vegetable vegetableByName = null;
        for (Vegetable vegetable : vegetableStorage) {
            if (vegetable.getName().equalsIgnoreCase(vegetableName)) {
                vegetableByName = vegetable;
                break;
            }
        }
        LOGGER.debug("The vegetable [{}] with name [{}] obtained from a vegetable storage", vegetableByName, vegetableName);
        return vegetableByName;
    }

    @Override
    public String toString() {
        return "RestaurantVegetableStorage{ " +
                "tomatoFactory=" + tomatoFactory +
                ", cucumberFactory=" + cucumberFactory +
                ", pepperFactory=" + pepperFactory +
                ", cabbageFactory=" + cabbageFactory +
                '}';
    }
}
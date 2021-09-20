package com.vadzimvincho.cook.vegetables.vegetablesupplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VegetableSupplier {
    private static final Logger LOGGER = LoggerFactory.getLogger(VegetableSupplier.class);

    private static final File file = new File("src/main/resources/vegetable.txt"); //todo

    public static List<List<String>> getVegetablesFromFile(String vegetableName) {
        List<List<String>> list;
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            list = linesStream.map(line -> Stream.of(line.split(" "))
                            .collect(Collectors.toList()))
                    .filter(lineList -> lineList.get(0).contains(vegetableName))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("File read error. " + e.getMessage());
            throw new RuntimeException("File read error. " + e.getMessage());
        }
        LOGGER.debug("Obtain list [{}] of vegetables from file [{}]", list, file);
        return list;
    }
}
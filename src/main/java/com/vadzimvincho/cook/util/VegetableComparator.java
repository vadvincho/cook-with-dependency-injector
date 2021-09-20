package com.vadzimvincho.cook.util;

import com.vadzimvincho.cook.model.vegetable.Vegetable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum VegetableComparator {
    SORT_BY_CALORIES{
        @Override
        public List<Vegetable> sort(List<Vegetable> vegetables) {
            return vegetables
                    .stream()
                    .sorted(Comparator.comparingInt(vegetable -> vegetable.getCalories()))
                    .collect(Collectors.toList());
        }
    },
    SORT_BY_NAME {
        @Override
        public List<Vegetable> sort(List<Vegetable> vegetables) {
            return vegetables
                    .stream()
                    .sorted(Comparator.comparing(vegetable -> vegetable.getName()))
                    .collect(Collectors.toList());
        }
    };

    public abstract List<Vegetable> sort(List<Vegetable> vegetables);
}
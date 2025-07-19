package com.example;

import java.util.List;

public class Animal {

    public List<String> getFood(String animalKind) {
        if ("Травоядное".equals(animalKind)) {
            return List.of("Трава", "Различные растения");
        } else if ("Хищник".equals(animalKind)) {
            return List.of("Животные", "Птицы", "Рыба");
        } else {
            try {
                throw new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getFamily() {
        return "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
    }
}
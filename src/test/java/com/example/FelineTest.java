package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    @Spy
    Feline felineSpy = new Feline();

    @Test
    void eatMeatTest() throws Exception {
        Feline feline = new Feline();
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = feline.eatMeat();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    void getFamilyTest() {
        Feline feline = new Feline();
        String expectedFamily = "Кошачьи";
        String actualFamily = feline.getFamily();
        assertEquals(expectedFamily, actualFamily);
    }

    @Test
    void getKittensWithoutArgsTest() {
        int actualKittens = felineSpy.getKittens();
        assertEquals(1, actualKittens);
    }

    @Test
    void getKittensWithArgsTest() {
        int actualKittens = felineSpy.getKittens(5);
        assertEquals(5, actualKittens);
    }

    @Test
    void getKittensWithArgsNoSpyTest() {
        Feline feline = new Feline();
        int expectedKittens = 3;
        int actualKittens = feline.getKittens(3);
        assertEquals(expectedKittens, actualKittens);
    }
}
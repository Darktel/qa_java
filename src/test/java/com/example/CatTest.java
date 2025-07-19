package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CatTest {

    @Mock
    Feline feline = new Feline();

    @Spy
    Cat catSpy;

    @BeforeEach
    void setUp() {
         catSpy = new Cat(feline);
    }

    @Test
    void testGetSoundPassed() {
        assertEquals("Мяу", catSpy.getSound());
    }

    @Test
    void testGetFoodPassed() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = catSpy.getFood();
        assertEquals(expectedFood, actualFood);
    }
}
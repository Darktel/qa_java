package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    Feline feline = new Feline();

    @Test
    @DisplayName("Проверка метода getKittens")
    void testGetKittens() throws Exception {

        Lion lion1 = new Lion("Самец", feline);


        when(feline.getKittens()).thenReturn(3);
        assertEquals(3, lion1.getKittens());
        verify(feline, times(1)).getKittens();
    }


    @Test
    @DisplayName("Проверка метода getFood")
    void testGetFood() throws Exception {
        Lion lion1 = new Lion("Самец", feline);

        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);

        List<String> actualFood = lion1.getFood();
        assertEquals(expectedFood, actualFood);
        verify(feline, times(1)).getFood("Хищник");
    }

    @Test
    void testDoesHaveManePassed() throws Exception {
        Lion lion1 = new Lion("Самец", feline);
        assertTrue(lion1.doesHaveMane());
    }

    @Test
    void testDoesHaveManeFailed() throws Exception {
        Lion lion1 = new Lion("Самка", feline);
        assertFalse(lion1.doesHaveMane());
    }



    @Test
    void testDoesHaveManeInvalidSex() {
        Exception exception = assertThrows(Exception.class, () -> new Lion("Неизвестно", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }
}


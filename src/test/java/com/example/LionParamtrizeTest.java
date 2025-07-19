package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LionParamtrizeTest {

    @Mock
    Feline feline = new Feline();


    // Параметризованный тест
    @ParameterizedTest
    @DisplayName("Параметризованный тест на создание экземпляра Lion")
    @ValueSource(strings = {"Самец", "Самка"})
    void createLionTest(String sex) throws Exception {
        Lion lion1 = new Lion(sex, feline);
        assertNotNull(lion1);
        if (sex.equals("Самец"))
            assertTrue(lion1.doesHaveMane());
        else if (sex.equals("Самка")) {
            assertFalse(lion1.doesHaveMane());
        }
    }

}

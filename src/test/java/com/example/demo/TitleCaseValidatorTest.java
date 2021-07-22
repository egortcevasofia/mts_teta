package com.example.demo;

import com.example.demo.validator.TitleCaseValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TitleCaseValidatorTest {

    @Autowired
    public TitleCaseValidator titleCaseValidator;


    @Test
    public void test_isValidAny() {
        assertEquals(titleCaseValidator.isValidAny(" Логические задачи"), false);
        assertEquals(titleCaseValidator.isValidAny("Логические Tasks"), false);
        assertEquals(titleCaseValidator.isValidAny("Логические  задачи"), false);
        assertEquals(titleCaseValidator.isValidAny("Логические задачи!"), false);
        assertEquals(titleCaseValidator.isValidAny("Логические Задачи"), true);
        assertEquals(titleCaseValidator.isValidAny("Логические Задачи, и Алгоритмы"), true);
    }

    @Test
    public void test_isValidRussian() {
        assertEquals(titleCaseValidator.isValidRussian("Логические задачи"), true);
        assertEquals(titleCaseValidator.isValidRussian("Логические Задачи, и Алгоритмы"), false);
        assertEquals(titleCaseValidator.isValidRussian("Логические задачи, и алгоритмы"), true);
        assertEquals(titleCaseValidator.isValidRussian("логические задачи, и алгоритмы"), false);
    }

    @Test
    public void test_isValidEnglish() {
        assertEquals(titleCaseValidator.isValidEnglish("Logical Task"), true);
        assertEquals(titleCaseValidator.isValidEnglish("Logical task"), false);
        assertEquals(titleCaseValidator.isValidEnglish("Logical an Task"), true);
        assertEquals(titleCaseValidator.isValidEnglish("Logical An Task"), false);
    }

    @Test
    void shouldReturnFalseIfStringIsEmpty() {
        assertEquals(titleCaseValidator.isValid("", null), false);
    }

    @Test
    void shouldReturnFalseIfStringIsNull() {
        assertEquals(titleCaseValidator.isValid(null, null), false);
    }



}

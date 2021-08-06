package com.example.demo.validator;

import com.example.demo.validatorUtils.EnglishRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnglishRulesTest {
    @Test
    public void test_hasCapitalLetter(){
        assertEquals(EnglishRules.isValidCapitalLetterFirstLast("Qwerty qwerty e Qwerty"), true);
        assertEquals(EnglishRules.isValidCapitalLetterFirstLast("Qwerty qwerty e qwerty"), false);
        assertEquals(EnglishRules.isValidCapitalLetterFirstLast("qwerty qwerty e Q werty"), false);
    }

    @Test
    public void test_hasCapitalLetterPreposition(){
        assertEquals(EnglishRules.isValidCapitalLetterPreposition("Qwerty For e Or Qwerty"), false);
        assertEquals(EnglishRules.isValidCapitalLetterPreposition("Qwerty e An Qwerty"), false);
        assertEquals(EnglishRules.isValidCapitalLetterPreposition("But Qwerty e Qwerty"), true);
        assertEquals(EnglishRules.isValidCapitalLetterPreposition("Qwerty e or Qwerty"), true);
    }

    @Test
    public void test_hasCapitalLetterOther(){
        assertEquals(EnglishRules.isValidCapitalLetterOther("Qwerty for Qwerty"), true);
        assertEquals(EnglishRules.isValidCapitalLetterOther("Qwerty for"), true);
        assertEquals(EnglishRules.isValidCapitalLetterOther("Qwerty for dfsd dfsd sdfsdf f f qwerty"), false);
        assertEquals(EnglishRules.isValidCapitalLetterOther("Qwerty qwerty Qwerty"), false);


    }


}

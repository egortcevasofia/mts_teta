package com.example.demo.validator;

import com.example.demo.validatorUtils.GeneralRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GeneralRulesTest {
    @Test
    public void test_isValidNeLineTabCarriege(){
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty/n"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty//n"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("/nqwerty"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty/t"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty"), true);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty//r"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("/rqwerty"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty/n"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty"), true);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty//t"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("/tqwerty"), false);
        assertEquals(GeneralRules.isValidNewLineTabCarriege("qwerty"), true);
    }


    @Test
    public void test_isValidMoreThanOneSpace(){
        assertEquals(GeneralRules.isValidMoreThanOneSpace("qwerty  "), false);
        assertEquals(GeneralRules.isValidMoreThanOneSpace("qwerty   "), false);
        assertEquals(GeneralRules.isValidMoreThanOneSpace("qwerty    "), false);
        assertEquals(GeneralRules.isValidMoreThanOneSpace("qwerty"), true);
        assertEquals(GeneralRules.isValidMoreThanOneSpace("qwerty "), true);
        assertEquals(GeneralRules.isValidMoreThanOneSpace("qw e rty "), true);
    }

    @Test
    public void test_isValidFirstLastSpace(){
        assertEquals(GeneralRules.isValidFirstLastSpace(" qwerty"), false);
        assertEquals(GeneralRules.isValidFirstLastSpace("qwerty "), false);
        assertEquals(GeneralRules.isValidFirstLastSpace("qwerty"), true);
    }

    @Test
    public void test_isValidEnglishRussian(){
        assertEquals(GeneralRules.isValidEnglishRussian("iqуке"), false);
        assertEquals(GeneralRules.isValidEnglishRussian("QWERTYйцукен"), false);
        assertEquals(GeneralRules.isValidEnglishRussian("qwertyЙЦУКЕН"), false);
        assertEquals(GeneralRules.isValidEnglishRussian("qwerty ЙЦУКЕН"), false);
        assertEquals(GeneralRules.isValidEnglishRussian("йцукн qwerty"), false);
        assertEquals(GeneralRules.isValidEnglishRussian("qwerty"), true);
        assertEquals(GeneralRules.isValidEnglishRussian("привет"), true);

    }

    @Test
    public void test_isValidForbiddenChar(){
        assertEquals(GeneralRules.isValidForbiddenChar("qwerty!"), false);
        assertEquals(GeneralRules.isValidForbiddenChar("qwe@rty!"), false);
        assertEquals(GeneralRules.isValidForbiddenChar(")qwerty!"), false);
        assertEquals(GeneralRules.isValidForbiddenChar("   ="), false);
        assertEquals(GeneralRules.isValidForbiddenChar("qwerty"), true);
        assertEquals(GeneralRules.isValidForbiddenChar("qwert,y"), true);

    }
}
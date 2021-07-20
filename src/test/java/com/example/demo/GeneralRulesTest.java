package com.example.demo;

import com.example.demo.validatorUtils.GeneralRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GeneralRulesTest {
    @Test
    public void test_isValidNeLine(){
        assertEquals(GeneralRules.isValidNewLine("qwerty/n"), false);
        assertEquals(GeneralRules.isValidNewLine("qwerty//n"), false);
        assertEquals(GeneralRules.isValidNewLine("/nqwerty"), false);
        assertEquals(GeneralRules.isValidNewLine("qwerty/t"), true);
        assertEquals(GeneralRules.isValidNewLine("qwerty"), true);
    }

    @Test
    public void test_isValidTab(){
        assertEquals(GeneralRules.isValidTab("qwerty/t"), false);
        assertEquals(GeneralRules.isValidTab("qwerty//t"), false);
        assertEquals(GeneralRules.isValidTab("/tqwerty"), false);
        assertEquals(GeneralRules.isValidTab("qwerty/n"), true);
        assertEquals(GeneralRules.isValidTab("qwerty"), true);
    }

    @Test
    public void test_isValidCarriage(){
        assertEquals(GeneralRules.isValidCarriage("qwerty/r"), false);
        assertEquals(GeneralRules.isValidCarriage("qwerty//r"), false);
        assertEquals(GeneralRules.isValidCarriage("/rqwerty"), false);
        assertEquals(GeneralRules.isValidCarriage("qwerty/n"), true);
        assertEquals(GeneralRules.isValidCarriage("qwerty"), true);
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
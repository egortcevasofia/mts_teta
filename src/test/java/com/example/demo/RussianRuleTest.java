package com.example.demo;

import com.example.demo.validatorUtils.RussianRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RussianRuleTest {
    @Test
    public void test_hasCapitalLetterFirst(){
        assertEquals(RussianRules.isValidCapitalLetterFirst("Йцукен йцукен й"), true);
        assertEquals(RussianRules.isValidCapitalLetterFirst("Й йцукен уйй"), true);
        assertEquals(RussianRules.isValidCapitalLetterFirst("йцукен"), false);
    }

    @Test
    public void test_hasSmallLetterOther(){
        assertEquals(RussianRules.isValidSmallLetterOther("Йцукен йцукен й"), true);
        assertEquals(RussianRules.isValidSmallLetterOther("Й йцукен уйй"), true);
        assertEquals(RussianRules.isValidSmallLetterOther("йцукен Йцукен йцуке Й"), false);
    }




}

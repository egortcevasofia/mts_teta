package com.example.demo.validatorUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnglishRules {

    public static Boolean isValidCapitalLetterFirstLast(String title) {
        String[] words = title.split(" ");
        return words[0].matches("[A-Z][a-z]*")
                && words[words.length - 1].matches("[A-Z][a-z]*");
    }


    public static Boolean isValidCapitalLetterPreposition(String title) {
        String[] words = title.split(" ");
        Set<String> prepositionSet = new HashSet<String>(Arrays.asList("A", "But",
                "For", "Or", "Not", "The", "An"));
        for (int i = 1; i < words.length - 1; i++) {
            if (prepositionSet.contains(words[i])) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isValidCapitalLetterOther(String title) {
        String[] words = title.split(" ");
        Set<String> prepositionSet = new HashSet<String>(Arrays.asList("a", "but",
                "for", "or", "not", "the", "an"));
        for (int i = 0; i < words.length - 1; i++) {
            if (!words[i].matches("[A-Z][a-z]*") && !prepositionSet.contains(words[i])) {
                return false;
            }
        }
        return true;
    }

}

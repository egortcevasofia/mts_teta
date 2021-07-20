package com.example.demo.validatorUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneralRules {

    public static Boolean isValidNewLine(String title) {
        return !title.contains("/n") && !title.contains("//n");
    }

    public static Boolean isValidTab(String title) {
        return !title.contains("/t") && !title.contains("//t");
    }

    public static Boolean isValidCarriage(String title) {
        return !title.contains("/r") && !title.contains("//r");
    }

    public static Boolean isValidMoreThanOneSpace(String title) {
        return !title.contains("  ")
                && !title.contains("   ")
                && !title.contains("    ");
    }

    public static Boolean isValidFirstLastSpace(String title) {
        return !title.startsWith(" ") && !title.endsWith(" ");
    }

    public static Boolean isValidEnglishRussian(String title) {
        Set<Character> charSet = new HashSet<Character>(List.of(' ', ',', '"', ':'));
        int countCyrillic = 0;
        int countLatin = 0;
        char[] titleChar = title.toCharArray();
        for (Character c : titleChar) {
            if (charSet.contains(c)) {

            } else if (Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC)) {
                countCyrillic++;
            } else if (Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.BASIC_LATIN)) {
                countLatin++;
            }
        }

        return countCyrillic == 0 || countLatin == 0;
    }


    public static Boolean isValidForbiddenChar(String title) {
        Set<Character> charSet = new HashSet<Character>(Arrays.<Character>asList('!',
                '@', '#', '$', '%', '^', '&',
                '?', '*', '(', ')', '-', '_',
                '+', '=', '|', '/', '\\', '<', '>', '.'));

        for (Character s : title.toCharArray()) {
            if (charSet.contains(s)) {
                return false;
            }
        }
        return true;
    }


}

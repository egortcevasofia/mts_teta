package com.example.demo.validatorUtils;

public class RussianRules {

    public static Boolean isValidCapitalLetterFirst(String title) {
        String[] words = title.split(" ");
        return words[0].matches("[А-Я][а-я]*");
    }

    public static Boolean isValidSmallLetterOther(String title) {
        String[] words = title.split(" ");
        for (int i = 1; i < words.length - 1; i++) {
            if (words[i].matches("\"?[А-ЯЁ][а-яё]*'?[а-яё]*[,:\"]?")) {
                return false;
            }
        }
        return true;
    }
}

package com.example.demo.validator;


import com.example.demo.annotation.TitleCase;
import com.example.demo.domain.Language;
import com.example.demo.validatorUtils.EnglishRules;
import com.example.demo.validatorUtils.GeneralRules;
import com.example.demo.validatorUtils.RussianRules;
import org.springframework.stereotype.Component;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class TitleCaseValidator implements ConstraintValidator<TitleCase, String> {

    private Language language;

    @Override
    public void initialize(TitleCase constraintAnnotation) {
        language = constraintAnnotation.language();
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        if (language.equals(Language.RU)) {
            return isValidRussian(title);
        }
        if (language.equals(Language.EN)) {
            return isValidEnglish(title);
        }
        if (language.equals(Language.ANY)) {
            return isValidAny(title);
        }
        return false;
    }

    public Boolean isValidRussian(String title) {
        return RussianRules.isValidCapitalLetterFirst(title)
                && RussianRules.isValidSmallLetterOther(title)
                && isValidAny(title);
    }

    public Boolean isValidEnglish(String title) {
        return EnglishRules.isValidCapitalLetterFirstLast(title)
                && EnglishRules.isValidCapitalLetterPreposition(title)
                && EnglishRules.isValidCapitalLetterOther(title)
                && isValidAny(title);
    }

    public Boolean isValidAny(String title) {
        return GeneralRules.isValidNewLineTabCarriege(title)
                && GeneralRules.isValidMoreThanOneSpace(title)
                && GeneralRules.isValidFirstLastSpace(title)
                && GeneralRules.isValidEnglishRussian(title)
                && GeneralRules.isValidForbiddenChar(title);

    }


}
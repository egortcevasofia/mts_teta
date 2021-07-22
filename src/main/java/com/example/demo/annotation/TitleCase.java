package com.example.demo.annotation;


import com.example.demo.domain.Language;
import com.example.demo.validator.TitleCaseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TitleCaseValidator.class)
public @interface TitleCase {

    Language language() default Language.ANY;

    String message() default "Not valid title";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

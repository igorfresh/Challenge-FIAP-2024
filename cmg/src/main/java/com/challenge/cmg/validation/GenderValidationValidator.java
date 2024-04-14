package com.challenge.cmg.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidationValidator implements ConstraintValidator<GenderValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("MASCULINO") || value.equals("FEMININO");
    }
    

}

package com.whatsapp.validations;

import com.whatsapp.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValidator, String> {
    private static final String PASSWORD_PATTERN = "^(?=(?:.*\\d){2,})(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";

    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && password.matches("^(?=(?:.*\\d){2,})(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$");
    }
}
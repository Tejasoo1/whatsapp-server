package com.whatsapp.annotation;

import com.whatsapp.validations.PasswordConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(
        validatedBy = {PasswordConstraintValidator.class}
)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Password must be at least 8 characters long, include at least 1 uppercase letter, 2 numbers, and 1 special character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

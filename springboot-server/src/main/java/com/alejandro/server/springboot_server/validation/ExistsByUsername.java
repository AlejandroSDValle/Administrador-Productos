package com.alejandro.server.springboot_server.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsByUsernameValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExistsByUsername {
    String message() default "Ya existe en la base de datos!, escoja otro username";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}

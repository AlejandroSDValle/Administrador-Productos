package com.alejandro.server.springboot_server.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.alejandro.server.springboot_server.entities.User;

public class UserValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", null, "user es requerido!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "password es requerido!");
    }

}

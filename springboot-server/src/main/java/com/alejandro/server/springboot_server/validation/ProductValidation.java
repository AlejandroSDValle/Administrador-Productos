package com.alejandro.server.springboot_server.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.alejandro.server.springboot_server.entities.Product;


@Component
public class ProductValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "nombre es requerido!");

        if(product.getPrice() <= 0){
            errors.rejectValue("price", null,  "precio es requerido!");
        }else if(product.getPrice() < 100 ){
            errors.rejectValue("price",null,  "tiene que ser mayor a 100");
        }
    }

}

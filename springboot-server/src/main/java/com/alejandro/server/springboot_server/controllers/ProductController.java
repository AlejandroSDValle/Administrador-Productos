package com.alejandro.server.springboot_server.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.alejandro.server.springboot_server.entities.Product;
import com.alejandro.server.springboot_server.services.ProductService;
import com.alejandro.server.springboot_server.validation.ProductValidation;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired 
    ProductValidation validation;

    @GetMapping
    public ResponseEntity<Iterable<Product>> listarProductos() {
        return new ResponseEntity<>(productService.listarProductos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productService.obtenerProducto(id));
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Product producto, BindingResult result) {
        validation.validate(producto, result);
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return new ResponseEntity<>(productService.crearProducto(producto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@Valid @RequestBody Product producto, BindingResult result, @PathVariable Long id) {
        validation.validate(producto, result);
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.ok(productService.actualizarProducto(id, producto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> actualizarAvailability(@PathVariable Long id) {
        productService.actualizarAvailability(id);
        return new ResponseEntity<>("Disponibilidad actualizada correctamente", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}


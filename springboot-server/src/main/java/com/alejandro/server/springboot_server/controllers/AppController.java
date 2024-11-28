package com.alejandro.server.springboot_server.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.alejandro.server.springboot_server.entities.Product;
import com.alejandro.server.springboot_server.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/")
public class AppController {

    @Autowired
    private ProductService productService;

    @GetMapping("/productos")
    public ResponseEntity<Iterable<Product>> listarProductos() {
        return new ResponseEntity<>(productService.listarProductos(), HttpStatus.OK);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Product> obtenerProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productService.obtenerProducto(id));
    }

    @PostMapping("/productos")
    public ResponseEntity<Product> crearProducto(@RequestBody Product producto) {
        return new ResponseEntity<>(productService.crearProducto(producto), HttpStatus.CREATED);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Product> actualizarProducto(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.actualizarProducto(id, product));
    }

    @PatchMapping("/productos/{id}")
    public ResponseEntity<String> actualizarAvailability(@PathVariable Long id) {
        productService.actualizarAvailability(id);
        return new ResponseEntity<>("Disponibilidad actualizada correctamente", HttpStatus.OK);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


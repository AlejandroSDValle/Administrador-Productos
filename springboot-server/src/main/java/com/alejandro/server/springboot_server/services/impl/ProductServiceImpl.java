package com.alejandro.server.springboot_server.services.impl;

import com.alejandro.server.springboot_server.entities.Product;
import com.alejandro.server.springboot_server.exceptions.ProductNotFoundException;
import com.alejandro.server.springboot_server.repositories.ProductRepository;
import com.alejandro.server.springboot_server.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> listarProductos() {
        return productRepository.findAll();
    }

    public Product obtenerProducto(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Error: el producto no existe"));
    }

    public Product crearProducto(Product producto) {
        if (producto.getName() == null || producto.getName().trim().isEmpty() || producto.getPrice() <= 0) {
            throw new IllegalArgumentException("Todos los campos son obligatorios y deben ser válidos");
        }
        return productRepository.save(producto);
    }

    public Product actualizarProducto(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Error: el producto con el ID " + id + " no existe");
        }
        if (product.getName() == null || product.getName().trim().isEmpty() || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Todos los campos son obligatorios y deben ser válidos");
        }
        product.setId(id);
        return productRepository.save(product);
    }

    public void actualizarAvailability(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Error: el producto con el ID " + id + " no existe"));
        boolean nuevaDisponibilidad = !product.isAvailability();
        product.setAvailability(nuevaDisponibilidad);
        productRepository.save(product);
    }

    public void eliminarProducto(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Error: el producto con el ID " + id + " no existe");
        }
        productRepository.deleteById(id);
    }
}

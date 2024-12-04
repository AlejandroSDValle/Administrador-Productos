package com.alejandro.server.springboot_server.services;

import com.alejandro.server.springboot_server.entities.Product;

public interface ProductService {
 
    public Iterable<Product> listarProductos();

    public Product obtenerProducto(Long id);

    public Product crearProducto(Product producto);

    public Product actualizarProducto(Long id, Product product);

    public void actualizarAvailability(Long id);

    public void eliminarProducto(Long id);
}

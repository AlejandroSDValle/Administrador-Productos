package com.alejandro.server.springboot_server.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.server.springboot_server.entities.Product;


public interface ProductRepository extends CrudRepository<Product, Long>{

}

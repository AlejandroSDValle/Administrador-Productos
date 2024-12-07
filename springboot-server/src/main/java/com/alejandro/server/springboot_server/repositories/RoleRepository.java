package com.alejandro.server.springboot_server.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.server.springboot_server.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

    Optional<Role> findByName(String name);
}

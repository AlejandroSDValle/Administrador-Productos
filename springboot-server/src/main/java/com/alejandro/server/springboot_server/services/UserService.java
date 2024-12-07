package com.alejandro.server.springboot_server.services;

import java.util.List;

import com.alejandro.server.springboot_server.entities.User;

public interface UserService {
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);

}

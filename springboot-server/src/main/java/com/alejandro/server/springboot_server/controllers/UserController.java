package com.alejandro.server.springboot_server.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alejandro.server.springboot_server.entities.User;
import com.alejandro.server.springboot_server.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> listarUser() {
        return userService.findAll();
    }
    

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        user.setAdmin(false);
        return create(user, result);
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errores);
    }
}

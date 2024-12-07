package com.alejandro.server.springboot_server.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alejandro.server.springboot_server.entities.User;
import com.alejandro.server.springboot_server.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

// Busca al usuraio por username cuando realiza un login 
@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Username %s no se encuentra registrado", username));
        }

        User user = userOptional.orElseThrow();
        
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.isEnabled(),
            true, // La cuenta no ha expirado
    true, // Las credenciales no han expirado
    true, // La cuenta no está bloqueada
            authorities 
        );
    }


}

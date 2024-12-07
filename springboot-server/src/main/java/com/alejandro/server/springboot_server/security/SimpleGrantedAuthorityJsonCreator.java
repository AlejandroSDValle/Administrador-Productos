package com.alejandro.server.springboot_server.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleGrantedAuthorityJsonCreator {

    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){

    }
}

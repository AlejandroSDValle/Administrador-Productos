package com.alejandro.server.springboot_server.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class Error {

    private String message;
    private String error;
    private int status;
    private Date date;


}

package com.example.EmployeeService.exception;

import lombok.Getter;
import lombok.Setter;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource not found on server");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}


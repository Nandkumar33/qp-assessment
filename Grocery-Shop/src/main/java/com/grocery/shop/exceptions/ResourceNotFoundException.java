package com.grocery.shop.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Requested Resource Not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

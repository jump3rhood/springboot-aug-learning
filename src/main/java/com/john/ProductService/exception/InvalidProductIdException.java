package com.john.ProductService.exception;

public class InvalidProductIdException extends Exception {
    public InvalidProductIdException() {
    }
    public InvalidProductIdException(String message) {
        super(message);
    }
}

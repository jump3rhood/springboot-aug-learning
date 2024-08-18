package com.john.ProductService.advice;

import com.john.ProductService.dto.ErrorDTO;
import com.john.ProductService.exception.InvalidProductIdException;
import com.john.ProductService.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ErrorDTO> handleInvalidProductIdException(InvalidProductIdException ex){
        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(ex.getMessage());
        dto.setCode("product-not-found");
        return new ResponseEntity<>(new ErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException ex){

        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(ex.getMessage());
        dto.setCode("product-not-found");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex){
        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(ex.getMessage() == null ? "Oops... Something went wrong!" : ex.getMessage());
        dto.setCode("internal-error");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}

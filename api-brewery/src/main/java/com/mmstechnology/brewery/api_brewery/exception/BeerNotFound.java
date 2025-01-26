package com.mmstechnology.brewery.api_brewery.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class BeerNotFound extends RuntimeException {
    public BeerNotFound(String message) {
        super(message);
    }
}

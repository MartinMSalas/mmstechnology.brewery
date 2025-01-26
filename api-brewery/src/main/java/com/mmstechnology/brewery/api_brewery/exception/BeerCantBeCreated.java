package com.mmstechnology.brewery.api_brewery.exception;

public class BeerCantBeCreated extends RuntimeException {
    public BeerCantBeCreated(String message) {
        super(message);
    }
}

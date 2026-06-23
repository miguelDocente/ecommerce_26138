package com.ecommerce.ecommerce.exception;

public class CarritoNoEncontradoException extends RuntimeException {

    public CarritoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
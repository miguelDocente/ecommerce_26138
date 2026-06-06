package com.ecommerce.ecommerce.exception;

// [REPASO] Nueva excepción.
// Se lanza cuando se busca una categoría por id y no existe.
public class CategoriaNoEncontradaException extends RuntimeException {

    public CategoriaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

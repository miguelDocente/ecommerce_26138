package com.ecommerce.ecommerce.exception;


// [REPASO] Nueva excepción.
// Se lanza cuando se intenta guardar o actualizar un producto con precio <= 0.
public class PrecioInvalidoException extends RuntimeException {

    public PrecioInvalidoException(String mensaje) {
        super(mensaje);
    }
}

package com.ecommerce.ecommerce.exception;

// [REPASO] Nueva excepción.
// Se lanza cuando se intenta guardar o actualizar una categoría con nombre vacío o nulo.
public class CategoriaNombreInvalidoException extends RuntimeException {

    public CategoriaNombreInvalidoException(String mensaje) {
        super(mensaje);
    }
}

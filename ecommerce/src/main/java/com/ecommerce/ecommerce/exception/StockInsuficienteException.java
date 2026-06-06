package com.ecommerce.ecommerce.exception;

// Se lanza cuando el stock recibido es inválido (por ejemplo, negativo).
// Más adelante también servirá para validar compras del carrito.
public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

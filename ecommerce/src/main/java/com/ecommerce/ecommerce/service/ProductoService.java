package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.exception.PrecioInvalidoException; // [REPASO] Nuevo import
import com.ecommerce.ecommerce.exception.ProductoNoEncontradoException;
import com.ecommerce.ecommerce.exception.StockInsuficienteException; // [REPASO] Ahora sí se usa
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.repository.ProductoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    // Inyección por constructor: Spring pasa el repositorio.
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Producto guardar(Producto p) {
        // [REPASO] Validación agregada en clase de repaso.
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (p.getPrecio() <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero. Se recibió: " + p.getPrecio());
        }
        if (p.getStock() < 0) {
            throw new StockInsuficienteException("El stock no puede ser negativo. Se recibió: " + p.getStock());
        }

        return repository.save(p);
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    // READ: por id. findById devuelve Optional — si está vacío, lanzamos la
    // excepción.
    public Producto obtenerPorId(int id) {

        return repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No se encontró un producto con id " + id));
    }

    // UPDATE
    public Producto actualizar(int id, Producto datos) {
        // [REPASO] Mismas validaciones que en guardar().
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (datos.getPrecio() <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero. Se recibió: " + datos.getPrecio());
        }
        if (datos.getStock() < 0) {
            throw new StockInsuficienteException("El stock no puede ser negativo. Se recibió: " + datos.getStock());
        }

        // Si no existe, obtenerPorId lanza excepción y se corta acá.
        Producto p = obtenerPorId(id);

        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());

        return repository.save(p);
    }

    // DELETE
    public void eliminar(int id) {
        Producto p = obtenerPorId(id);
        repository.delete(p);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.buscarPorCategoria(categoria);
    }
}

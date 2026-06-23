package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.exception.ProductoNoEncontradoException;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.repository.ProductoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public Producto guardar(Producto p) {
        return repository.save(p);
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public Producto obtenerPorId(Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No se encontró un producto con id " + id));
    }

    // UPDATE
    public Producto actualizar(Integer id, Producto datos) {
        Producto p = obtenerPorId(id);

        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());
        if (datos.getImagenUrl() != null) {
            p.setImagenUrl(datos.getImagenUrl());
        }; // campo nuevo

        return repository.save(p);
    }

    public void eliminar(Integer id) {
        Producto p = obtenerPorId(id);
        repository.delete(p);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.findByCategoriaNombreContainingIgnoreCase(categoria);
    }
}

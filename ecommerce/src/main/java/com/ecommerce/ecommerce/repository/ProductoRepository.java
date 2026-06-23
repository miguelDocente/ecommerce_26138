package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Containing → LIKE %valor%
    List<Producto> findByNombreContaining(String nombre);

    // Navega la relación hacia Categoria.
    // IgnoreCase → sin distinción de mayúsculas
    // Containing → LIKE %valor%
    List<Producto> findByCategoriaNombreContainingIgnoreCase(String nombre);
}
package com.ecommerce.ecommerce.controller;

// [REPASO] Nueva clase: controlador REST para categorías.
// Mismo patrón que ProductoController.

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.exception.CategoriaNoEncontradaException;
import com.ecommerce.ecommerce.exception.CategoriaNombreInvalidoException;
import com.ecommerce.ecommerce.model.Categoria;
import com.ecommerce.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    // GET /categorias — 200 OK con la lista.
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // GET /categorias/{id} — 200 OK si existe, 404 si no.
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (CategoriaNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /categorias — 201 Created si es válida, 400 si el nombre está vacío.
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria nuevaCategoria) {
        try {
            Categoria creada = service.guardar(nuevaCategoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (CategoriaNombreInvalidoException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /categorias/{id} — 200 OK si existe, 404 si no, 400 si nombre vacío.
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable int id, @RequestBody Categoria datos) {
        try {
            return ResponseEntity.ok(service.actualizar(id, datos));
        } catch (CategoriaNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (CategoriaNombreInvalidoException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /categorias/{id} — 200 OK si existe, 404 si no.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (CategoriaNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

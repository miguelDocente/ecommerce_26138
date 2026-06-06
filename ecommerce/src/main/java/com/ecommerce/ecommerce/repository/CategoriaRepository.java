package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
} 

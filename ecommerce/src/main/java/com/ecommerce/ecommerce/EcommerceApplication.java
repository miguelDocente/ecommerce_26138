package com.ecommerce.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecommerce.ecommerce.model.Categoria;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.service.CategoriaService;
import com.ecommerce.ecommerce.service.ProductoService;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner cargarDatos(ProductoService productoService, CategoriaService categoriaService) {
        return args -> {
            if (categoriaService.listarTodas().isEmpty()) {
                Categoria almacen = categoriaService.guardar(new Categoria("Almacén", "Productos de almacén"));
                Categoria bebidas = categoriaService.guardar(new Categoria("Bebidas", "Bebidas y líquidos"));

                productoService.guardar(new Producto("Yerba 1kg", 3200, 50, almacen));
                productoService.guardar(new Producto("Aceite 1.5L", 4100, 30, almacen));
                productoService.guardar(new Producto("Agua 2L", 900, 80, bebidas));
            }
        };
    }
}

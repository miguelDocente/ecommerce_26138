package com.ecommerce.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.service.ProductoService;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	// Carga datos iniciales SOLO si la base está vacía.
    // Así evitamos duplicar los productos en cada reinicio.
    @Bean
    CommandLineRunner cargarDatos(ProductoService productoService) {
        return args -> {
            if (productoService.listarTodos().isEmpty()) {
                productoService.guardar(new Producto("Yerba 1kg", 3200, 50, "Almacén"));
                productoService.guardar(new Producto("Aceite 1.5L", 4100, 30, "Almacén"));
                productoService.guardar(new Producto("Fideos 500g", 1500, 80, "Almacén"));
            }
        };
    }
}

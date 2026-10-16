package com.dominio.PetShop.services;


import com.dominio.PetShop.models.Product;
import com.dominio.PetShop.repositories.ProductsRepository;  // Asegúrate de tener el repositorio correcto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;  // Inyectamos el repositorio

    // Método para obtener todos los productos
    public List<Product> getAllProducts() {
        return productsRepository.findAll();  // Obtiene todos los productos de la base de datos
    }
}

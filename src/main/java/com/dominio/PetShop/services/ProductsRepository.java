package com.dominio.PetShop.services;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dominio.PetShop.models.Product; 


public interface ProductsRepository extends JpaRepository<Product, Integer> {

}
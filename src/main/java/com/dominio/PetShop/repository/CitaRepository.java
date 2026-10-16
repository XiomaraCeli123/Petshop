package com.dominio.PetShop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dominio.PetShop.models.Cita; 


public interface CitaRepository extends JpaRepository<Cita, Long> {

}
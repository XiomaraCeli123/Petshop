package com.dominio.PetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dominio.PetShop.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	
	public AppUser findByEmail(String email);

}


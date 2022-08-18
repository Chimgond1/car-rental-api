package com.ty.carrentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;

import com.ty.carrentalapi.dto.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer>{
	@Query("Select p from Owner p where p.email=?1 and p.password=?2")
	  Owner validateOwner(String email,String password);
		
	

}

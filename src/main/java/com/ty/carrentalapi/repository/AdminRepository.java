package com.ty.carrentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;

import com.ty.carrentalapi.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	@Query("select y from Admin y where y.email=?1 and y.password=?2")
	Admin validateAdminLogIn(String email,String password);
		
	

}

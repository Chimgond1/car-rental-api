package com.ty.carrentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query("select y from Customer y where y.c_email=?1 and y.password=?2")
	Customer validateCustomerLogIn(String email,String password);
	
}

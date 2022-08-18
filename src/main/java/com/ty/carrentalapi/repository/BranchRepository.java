package com.ty.carrentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.carrentalapi.dto.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
	

}

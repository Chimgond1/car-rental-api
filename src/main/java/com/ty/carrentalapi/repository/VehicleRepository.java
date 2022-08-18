package com.ty.carrentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.carrentalapi.dto.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}

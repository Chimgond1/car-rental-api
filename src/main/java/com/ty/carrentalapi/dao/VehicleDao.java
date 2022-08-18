package com.ty.carrentalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.carrentalapi.dto.Vehicle;
import com.ty.carrentalapi.exception.InvalidIdException;
import com.ty.carrentalapi.repository.VehicleRepository;

@Repository
public class VehicleDao {
	@Autowired
	VehicleRepository vehicleRepository;
	@Autowired
	AdminDao adminDao;
	@Autowired
	BranchDao branchDao;

	public Vehicle saveVehicle(Vehicle vehicle, int a_id, int b_id) {
		if (adminDao.getAdminById(a_id) != null) {
			if (branchDao.getBranchbyId(b_id) != null) {
				vehicle.setAdmin(adminDao.getAdminById(a_id));
				vehicle.setBranch(branchDao.getBranchbyId(b_id));
				return vehicleRepository.save(vehicle);

			} else {
				throw new InvalidIdException("Enter Branch Id Is Wrong");

			}
		} else {
			throw new InvalidIdException("Enter Admin Id Is Wrong");
		}

	}

	public Vehicle getVehicleById(int v_id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(v_id);
		if (vehicle.isEmpty()) {
			return null;
		} else {
			return vehicle.get();
		}
	}

	public List<Vehicle> getAllVehicle() {
		return vehicleRepository.findAll();
	}

	public String deleteVehicle(int v_id) {
		Optional<Vehicle> optional = vehicleRepository.findById(v_id);
		if (optional.isEmpty()) {
			return "Empty";
		} else {
			vehicleRepository.delete(optional.get());
			return "Deleted";
		}
	}

	public Vehicle updateVehicle(Vehicle vehicle, int v_id,int a_id,int b_id) {
		Vehicle vehicle2=getVehicleById(v_id);
		if(vehicle.getId()!=0) {
			vehicle2.setId(vehicle.getId());
		}if(vehicle.getVehicle_model()!=null) {
			vehicle2.setVehicle_model(vehicle.getVehicle_model());
		}if(vehicle.getVehicle_no()!=null) {
			vehicle2.setVehicle_no(vehicle.getVehicle_no());
		}if(vehicle.getVehicle_rent_price()!=0) {
			vehicle2.setVehicle_rent_price(vehicle.getVehicle_rent_price());
		}if(vehicle.getVehicle_type()!=null) {
			vehicle2.setVehicle_type(vehicle.getVehicle_type());
		}
		vehicleRepository.save( vehicle2)	;	
		return vehicle2;
	}

}

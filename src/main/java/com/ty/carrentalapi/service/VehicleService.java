package com.ty.carrentalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.carrentalapi.dao.AdminDao;
import com.ty.carrentalapi.dao.BranchDao;
import com.ty.carrentalapi.dao.VehicleDao;
import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.dto.Vehicle;
import com.ty.carrentalapi.exception.InvalidIdException;

@Service
public class VehicleService {
	@Autowired
	VehicleDao vehicleDao;
	@Autowired
	AdminDao adminDao;
	@Autowired
	BranchDao branchDao;

	public ResponseEntity<ResponseStructure<Vehicle>>  saveVehicle(Vehicle vehicle, int a_id, int b_id) {
		ResponseStructure<Vehicle> responseStructure = new ResponseStructure<Vehicle>();
		if(adminDao.getAdminById(a_id)!=null) {
			if(branchDao.getBranchbyId(b_id)!=null) {
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("success");
				responseStructure.setData(vehicleDao.saveVehicle(vehicle, a_id, b_id));
				
			}else {
				throw new InvalidIdException("Enter Branch Id Is Wrong");

			}
		}else {
			throw new InvalidIdException("Enter Admin Id Is Wrong");
		}
		return new ResponseEntity<ResponseStructure<Vehicle>>(responseStructure,HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Vehicle>> getVehicleById(int v_id) {
		ResponseStructure<Vehicle> responseStructure = new ResponseStructure<Vehicle>();
		Vehicle vehicle = vehicleDao.getVehicleById(v_id);
		if (vehicle != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(vehicle);
		} else {
			throw new InvalidIdException("enter "+v_id +" Id Is Invalid");
		}
		return new ResponseEntity<ResponseStructure<Vehicle>>(responseStructure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Vehicle>>> findAllVehicle() {
		ResponseEntity<ResponseStructure<List<Vehicle>>> responseEntity=null;
		ResponseStructure<List<Vehicle>> responseStructure = new ResponseStructure<List<Vehicle>>();
		List<Vehicle> vehicles = vehicleDao.getAllVehicle();
		if (vehicles != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(vehicles);
			responseEntity=new ResponseEntity<ResponseStructure<List<Vehicle>>>(responseStructure,HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(" not success");
			responseStructure.setData(null);
			responseEntity=new ResponseEntity<ResponseStructure<List<Vehicle>>>(responseStructure,HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Vehicle>> updateVehicle(Vehicle vehicle, int v_id, int a_id, int b_id) {
		ResponseStructure<Vehicle> responseStructure = new ResponseStructure<Vehicle>();
		Vehicle vehicle2 = vehicleDao.getVehicleById(v_id);
		if (vehicleDao.getVehicleById(v_id)!= null) {
			if(adminDao.getAdminById(a_id)!=null) {
				if(branchDao.getBranchbyId(b_id)!=null) {
					responseStructure.setStatusCode(HttpStatus.OK.value());
					responseStructure.setMessage("success");
					responseStructure.setData(vehicleDao.updateVehicle(vehicle,v_id, a_id, b_id));

				}else {
					throw new InvalidIdException("enter Branch "+b_id +" Id Is Invalid");

				}
			}else {
				throw new InvalidIdException("enter Admin "+a_id +" Id Is Invalid");

			}
			} else {
			throw new InvalidIdException("enter Vehicle "+v_id +" Id Is Invalid");

		}
		return new ResponseEntity<ResponseStructure<Vehicle>>(responseStructure,HttpStatus.OK);

	}
	public ResponseEntity<ResponseStructure<String>> deleteVehicleById(int v_id){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		if(vehicleDao.getVehicleById(v_id)!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(vehicleDao.deleteVehicle(v_id));
		}else {
			throw new InvalidIdException("enter Vehicle "+v_id +" Id Is Invalid");

		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	}
}

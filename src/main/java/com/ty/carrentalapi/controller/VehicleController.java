package com.ty.carrentalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.dto.Vehicle;
import com.ty.carrentalapi.service.VehicleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class VehicleController {
	@Autowired
	VehicleService vehicleService;

	@PostMapping("/vehicles/{a_id}/{b_id}")
	@ApiOperation("To Save Vehicle By Branch Id And Admin Id")
	@ApiResponses({@ApiResponse(code = 200,message = "Save Vehicle Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Save Vehicle/Branch Or Admine Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Vehicle>> saveVehicle(@RequestBody Vehicle vehicle, @PathVariable @ApiParam("Admin Id") int a_id,
			@PathVariable @ApiParam("Branch Id") int b_id) {
		return vehicleService.saveVehicle(vehicle, a_id, b_id);
	}
	@GetMapping("/vehicles/{v_id}")
	@ApiOperation("To Get Vehicle By Vehicle Id")
	@ApiResponses({@ApiResponse(code = 200,message = "Get Vehicle Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get Vehicle/ Vehicle Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Vehicle>> getVehicleById(@PathVariable @ApiParam("Vehicle Id") int v_id) {
		return vehicleService.getVehicleById(v_id);
	}
	@GetMapping("/vehicles")
	@ApiOperation("To Get All Vehicle ")
	@ApiResponses({@ApiResponse(code = 200,message = "Get All Vehicle Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get All Vehicle"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<List<Vehicle>>> getAllVehicle() {
		return vehicleService.findAllVehicle();
	}
	@PutMapping("/vehicles/{v_id}/{a_id}/{b_id}")
	@ApiOperation("To Update Vehicle By Vehicle Id, Admin Id And Branch Id")
	@ApiResponses({@ApiResponse(code = 200,message = "Update Vehicle Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Update Vehicle/Branch Or Admine Or Vehicle Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Vehicle>> updateVehicle(@RequestBody Vehicle vehicle,@PathVariable @ApiParam("Vehicle Id") int v_id,@PathVariable @ApiParam("Admin Id") int a_id,@PathVariable @ApiParam("Branch Id") int b_id) {
		return vehicleService.updateVehicle(vehicle, v_id, a_id, b_id);
	}
	@DeleteMapping("/vehicles/{v_id}")
	@ApiOperation("To Delete Vehicle By Vehicle Id")
	@ApiResponses({@ApiResponse(code = 200,message = "Delete Vehicle Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Update Vehicle/Vehicle Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	
	public ResponseEntity<ResponseStructure<String>> deleteVehicleById(@PathVariable @ApiParam("Vehicle Id") int v_id){
		return vehicleService.deleteVehicleById(v_id);
	}
}

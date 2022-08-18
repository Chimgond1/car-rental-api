package com.ty.carrentalapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.carrentalapi.dto.LogIn;
import com.ty.carrentalapi.dto.Owner;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.service.OwnerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class OwnerController {
	@Autowired
	OwnerService ownerService;

	@PostMapping("/owners")
	@ApiOperation("Self Registation of Owner ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Save Owner Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Save Owner"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<Owner>> saveOwner(@RequestBody @Valid Owner owner) {
		return ownerService.saveOwner(owner);
	}

	@GetMapping("/owners/{o_id}")
	@ApiOperation("Getting of Owner By Owner Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Getting Owner  Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data /Owner Id is Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<Owner>> getOwner(@PathVariable @ApiParam("Owner Id") int o_id) {
		return ownerService.getOwner(o_id);
	}

	@PostMapping("/owners/login")
	@ApiOperation("Validation of Owner ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Validating Owner is Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Validate"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<Owner>> validateOwner(@RequestBody  LogIn login) {
		return ownerService.validateOwner(login.getEmail(), login.getPassword());
	}
	@GetMapping("/owners")
	@ApiOperation("Get All  Owner ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Get All Owner is Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Get All"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<List<Owner>>> getAllOwner() {
		return ownerService.getAllOwner();
	}

	@DeleteMapping("/owners/{o_id}")
	@ApiOperation("Delete of Owner By Owner Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Delete Owner  Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data /Owner Id is Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<String>> deleteOwner(@PathVariable @ApiParam("Owner Id") int o_id) {
		return ownerService.deleteOwner(o_id);
	}
	@PutMapping("/owners/{o_id}")
	@ApiOperation("Update Owner By  Owner Id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Update Owner Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Update Owner/Owner Id Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public  ResponseEntity<ResponseStructure<Owner>>  updateOwner(@RequestBody Owner owner,@PathVariable @ApiParam("Owner Id") int o_id) {
		return ownerService.updateOwner(owner, o_id);
	}
	

}

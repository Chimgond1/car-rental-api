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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.Branch;
import com.ty.carrentalapi.dto.LogIn;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.service.AdminService;
import com.ty.carrentalapi.service.BranchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BranchController {
	@Autowired
	BranchService branchService;

	@PostMapping("/branches/{h_id}")
	@ApiOperation("To Save Branch By HeadOffice Id ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Save Branch Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Save Branch/headoffice id Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody @Valid Branch branch,
			@PathVariable @ApiParam("HeadOffice Id") int h_id) {
		return branchService.savaBranch(branch, h_id);

	}

	@GetMapping("/branches/{b_id}")
	@ApiOperation("To Get Branch By Branch Id ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Get Branch Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Get Branch/Branch Id Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(@PathVariable @ApiParam("Branch Id") int b_id) {
		return branchService.getBranchById(b_id);
	}

	@GetMapping("/branches")
	@ApiOperation("To Get All Branch  ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Get All Branch Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Get All Branch"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		return branchService.findAllBranch();
	}

	@PutMapping("/branches/{b_id}/{h_id}")
	@ApiOperation("To Update Branch By Branch Id ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Update Branch Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Update Branch/Branch Id Does't found/HeadOffice Id Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody Branch branch,
			@PathVariable @ApiParam("Branch Id") int b_id, @PathVariable @ApiParam("HeadOffice Id") int h_id) {
		return branchService.updateBranch(branch, b_id, h_id);
	}
	@DeleteMapping("/branches/{b_id}")
	@ApiOperation("To Delete Branch By Branch Id ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Delete Branch Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Delete Branch/Branch Id Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<String>> deleteBranch(@PathVariable @ApiParam("Branch Id") int b_id){
		return branchService.deleteBranch(b_id);
	}

}

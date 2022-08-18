package com.ty.carrentalapi.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.LogIn;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.service.AdminService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AdminController {
	@Autowired
	AdminService adminService;

	@PostMapping("/admins/{b_id}")
	@ApiOperation("To Save Admin By Branch Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Save Admin Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Save Admin/Branch Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Admin>>  saveAdmin(@RequestBody Admin admin, @PathVariable @ApiParam("Branch Id") int b_id) {

		return adminService.saveAdmin(admin, b_id);
	}
	@GetMapping("/admins/{a_id}")
	@ApiOperation("To Get Admin By Admin Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Get Admin Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get Admin/Admin Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Admin>> getAdminById(@PathVariable @ApiParam("Admin Id") int a_id) {
		return adminService.getAdminById(a_id);
	}
	@GetMapping("/admins")
	@ApiOperation("To Get All Admin  ")
	@ApiResponses({@ApiResponse(code = 200,message = "Get All Admin Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get All Admin"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<List<Admin>>> findAllAdmin() {
		return adminService.findAllAdmin();
	}
	@PutMapping("/admins/{a_id}")
	@ApiOperation("To Update Admin By Admin Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Update Admin Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Update Admin/Admin Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin,@PathVariable @ApiParam("Admin Id") int a_id) {
		return adminService.updateAdmin(admin, a_id);
	}
	@PostMapping("/admins/login")
	@ApiOperation("To Validate Admin ")
	@ApiResponses({@ApiResponse(code = 200,message = "Validate Admin Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Validate Admin"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Admin>> validateAdminLogIn(@RequestBody LogIn login) {
		return adminService.validateAdminLogIn(login.getEmail(),login.getPassword());
	}
	@DeleteMapping("/admins/{a_id}")
	@ApiOperation("To Delete Admin ")
	@ApiResponses({@ApiResponse(code = 200,message = "Delete Admin Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Delete Admin/Admine Id Is Does't Found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<String>>  deleteAdmind(@PathVariable @ApiParam("Admin Id") int a_id) {
		return adminService.deleteAdmin(a_id);
	}
} 

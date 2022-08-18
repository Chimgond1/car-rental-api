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

import com.ty.carrentalapi.dto.Customer;
import com.ty.carrentalapi.dto.LogIn;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@PostMapping("/customers/{b_id}/{v_id}")
	@ApiOperation("To Save Customer By Branch Id And Vehicle Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Save Customer Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Save Customer/Branch Or Vehicle Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer, @PathVariable @ApiParam("Branch Id") int b_id,
			@PathVariable @ApiParam("Vehicle Id") int v_id) {
		return customerService.saveCustomer(customer, b_id, v_id);

	}
	
	@GetMapping("/customers/{c_id}")
	@ApiOperation("To Get Customer By Customer Id  ")
	@ApiResponses({@ApiResponse(code = 200,message = "Get Customer Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get Customer/Customer  Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable @ApiParam("Customer Id") int c_id) {
		return customerService.getCustomerById(c_id);
	}
	@GetMapping("/customers")
	@ApiOperation("To Get All Customer ")
	@ApiResponses({@ApiResponse(code = 200,message = "Get All Customer Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get All Customer"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {
		return customerService.findAllCustomer();
	}
    @PutMapping("/customers/{c_id}/{b_id}/{h_id}")
    @ApiOperation("To Update Customer By Customer Id ,Branch Id And HeadOffice Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Update Customer Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Update Customer/Customer,Branch And HeadOffice  Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer,@PathVariable @ApiParam("Customer Id") int c_id,@PathVariable @ApiParam("Branch Id") int b_id,@PathVariable @ApiParam("HeadOffice Id") int h_id) {
		return customerService.updateCustomer(customer, c_id, b_id, h_id);
	}
    @DeleteMapping("/customers/{c_id}")
    @ApiOperation("To Delete Customer By Customer Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Update Customer Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Delete Customer/Customer Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(@PathVariable @ApiParam("Customer Id") int c_id ){
    	return customerService.deleteCustomerById(c_id);
    }
    @PostMapping("/customers/login")
    @ApiOperation("To Validate Customer By Email And Password")
	@ApiResponses({@ApiResponse(code = 200,message = "Validate Customer Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Validate Customer/"),
        @ApiResponse(code = 500,message = "Internal Server Error")})	
	public ResponseEntity<ResponseStructure<Customer>>  validateCustomerLogIn(@RequestBody LogIn login) {
	return customerService.validateCustomerLogIn(login.getEmail(),login.getPassword());
	}
}

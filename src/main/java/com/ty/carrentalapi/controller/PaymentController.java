package com.ty.carrentalapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.carrentalapi.dto.Payment;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.service.PaymentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	@PostMapping("/payments/{c_id}/{v_id}/{b_id}")
	@ApiOperation("To Save Payment By Customer Id ,Vehicle Id And Branch Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Save Payment Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Save Payment/Vehicle,Customer And Branch  Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<Payment>> savePayment(@RequestBody Payment payment, @PathVariable @ApiParam("Customer Id") int c_id,
			@PathVariable @ApiParam("Vehicler Id") int v_id, @PathVariable @ApiParam("Branch Id") int b_id) {
		return paymentService.savePayment(payment, c_id, v_id, b_id);
	}
	@GetMapping("/payments/{p_id}")
	@ApiOperation("To Get Payment By Payment Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Get Payment Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get Payment/Payment  Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(@PathVariable @ApiParam("Payment Id") int p_id) {
		return paymentService.getPaymentById(p_id);
	}
	@GetMapping("/payments")
	@ApiOperation("To Get all Payment")
	@ApiResponses({@ApiResponse(code = 200,message = "Get All Payment Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get All Payment"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public  ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
		return paymentService.findAllPayment();
	}
	@PutMapping("/payments/{p_id}/{c_id}/{v_id}/{b_id}")
	@ApiOperation("To Update Payment By Customer Id ,Vehicle Id , Branch Id And Payment Id")
	@ApiResponses({@ApiResponse(code = 200,message = "Update Payment Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Update Payment/Vehicle,Customer , Branch And PayMent  Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<Payment>> updatePayment(Payment payment,@PathVariable @ApiParam("Payment Id") int p_id,@PathVariable @ApiParam("Customer Id") int c_id,@PathVariable @ApiParam("Vehicle Id") int v_id,@PathVariable @ApiParam("Branch Id") int b_id) {
		return paymentService.updateBooking(payment, p_id, c_id, v_id, b_id);
	}

}

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

import com.ty.carrentalapi.dto.Booking;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.service.BookingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BookingController {
	@Autowired
	BookingService bookingService;

	@PostMapping("/bookings/{v_id}/{c_id}")
	@ApiOperation("To Save Booking By Customer Id ,Vehicle Id  ")
	@ApiResponses({@ApiResponse(code = 200,message = "Save Booking Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to save Booking/Vehicle,Customer  Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@RequestBody Booking booking, @PathVariable @ApiParam("Vehicle Id") int v_id,@PathVariable @ApiParam("Customer Id") int c_id) {
		return bookingService.saveBooking(booking, v_id, c_id);

	}
	@GetMapping("/bookings/{b_id}")
	@ApiOperation("To Get Booking By Booking Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Get Booking Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get Booking/Booking Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<Booking>> getBookingByById(@PathVariable @ApiParam("Branch Id") int b_id) {
		return bookingService.getBookingById(b_id);
	}
	@GetMapping("/bookings")
	@ApiOperation("To Get All Booking ")
	@ApiResponses({@ApiResponse(code = 200,message = "Get All Booking Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Get all Booking"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking() {
		return bookingService.findAllBooking();
	}
	@PutMapping("/bookings/{b_id}/{v_id}/{c_id}")
	@ApiOperation("To Update Booking By Customer Id ,Vehicle Id And Branch Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Update Booking Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Update Booking/Vehicle,Customer And Branch  Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(@RequestBody Booking booking,@PathVariable @ApiParam("Branch Id") int b_id,@PathVariable @ApiParam("Vehicle Id") int v_id,@PathVariable @ApiParam("Customer Id") int c_id) {
		return bookingService.updateBooking(booking, b_id, v_id, c_id);
	}
	@DeleteMapping("/bookings/{b_id}")
	@ApiOperation("To Deleye Booking By Booking Id ")
	@ApiResponses({@ApiResponse(code = 200,message = "Delete Booking Successfully"),
        @ApiResponse(code = 400,message = "Bad Request for Data to Delete Booking/Booking Id Does't found"),
        @ApiResponse(code = 500,message = "Internal Server Error")})		
	public ResponseEntity<ResponseStructure<String>>  deletBooking(@PathVariable @ApiParam("Branch Id") int b_id) {
		return bookingService.deletBooking(b_id);
	}
}

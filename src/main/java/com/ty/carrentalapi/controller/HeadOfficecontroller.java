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

import com.ty.carrentalapi.dao.HeadOfficeDao;
import com.ty.carrentalapi.dto.HeadOffice;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.service.HeadOfficeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class HeadOfficecontroller {
	@Autowired
	HeadOfficeService headOfficeService;

	@PostMapping("/headofficies/{o_id}")
	@ApiOperation("To Save HeadOffice By Owner Id ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Save HeadOffice Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Save Owner/Owner Id Is Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<HeadOffice>> saveHeadOffice(@RequestBody @Valid HeadOffice headOffice,
			@PathVariable @ApiParam("Owner Id") int o_id) {
		return headOfficeService.saveHeadOffice(headOffice, o_id);

	}

	@GetMapping("/headofficies/{h_id}")
	@ApiOperation("To Get HeadOffice By HeadOffice Id ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Get HeadOffice Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data/ HeadOffice Id Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<HeadOffice>> getHeadOfficeById(
			@PathVariable @ApiParam("HeadOffice Id") int h_id) {
		return headOfficeService.getHeadOfficeById(h_id);
	}

	@GetMapping("/headofficies")
	@ApiOperation("To Get All HeadOffice  ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Get All HeadOffice Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<List<HeadOffice>>> getAllHeadOffice() {
		return headOfficeService.getAllHeadOffice();
	}

	@DeleteMapping("/headofficies/{h_id}")
	@ApiOperation("To Delete HeadOffice By HeadOffice Id ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Delete HeadOffice Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data/ HeadOffice Id Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<String>> deleteHeadOffice(
			@PathVariable @ApiParam("HeadOffice Id") int h_id) {
		return headOfficeService.deleteHeadOffice(h_id);
	}

	@PutMapping("/headofficies/{h_id}")
	@ApiOperation("To Update HeadOffice By Headoffice Id ")
	@ApiResponses({ @ApiResponse(code = 200, message = "Update HeadOffice Successfully"),
			@ApiResponse(code = 400, message = "Bad Request for Data to Save Owner/Headoffice Id Is Does't found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseStructure<HeadOffice>> updateHeadOffice(@RequestBody HeadOffice headOffice,
			@PathVariable @ApiParam("HeadOffice Id") int h_id) {
		return headOfficeService.updateHeadOffice(h_id, headOffice);
	}
}
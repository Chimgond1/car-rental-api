package com.ty.carrentalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.carrentalapi.dao.BranchDao;
import com.ty.carrentalapi.dao.CustomerDao;
import com.ty.carrentalapi.dao.VehicleDao;
import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.Customer;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.dto.Vehicle;
import com.ty.carrentalapi.exception.EnterPasswordOrEmailIsWorgException;
import com.ty.carrentalapi.exception.InvalidIdException;

@Service
public class CustomerService {
	@Autowired
	CustomerDao customerDao;
	@Autowired
	BranchDao branchDao;
	@Autowired
	VehicleDao vehicleDao;

	public ResponseEntity< ResponseStructure<Customer>>  saveCustomer(Customer customer, int b_id, int v_id) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		if(branchDao.getBranchbyId(b_id)!=null) {
			if(vehicleDao.getVehicleById(v_id)!=null) {
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("success");
				responseStructure.setData(customerDao.saveCustomer(customer, b_id, v_id));
				return new ResponseEntity< ResponseStructure<Customer>>(responseStructure,HttpStatus.CREATED);

			}else {
				throw new InvalidIdException("Enter Vehicle Id Wrong");

			}
		}else {
			throw new InvalidIdException("Enter Branch Id Wrong");
		}
		}
	
	

	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int c_id) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer = customerDao.getCustomerById(c_id);
		if (customer != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(customer);
		} else {
			throw new InvalidIdException("Enter Customer "+c_id+" Id Is Invalid ");
		}
		return new ResponseEntity<ResponseStructure<Customer>>(responseStructure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Customer>>> findAllCustomer() {
		ResponseEntity<ResponseStructure<List<Customer>>> responseEntity=null;
		ResponseStructure<List<Customer>> responseStructure = new ResponseStructure<List<Customer>>();
		List<Customer> customers = customerDao.getAllCustomer();
		if (customers != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(customers);
			responseEntity=new ResponseEntity<ResponseStructure<List<Customer>>>(responseStructure,HttpStatus.CREATED);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(" not success");
			responseStructure.setData(null);
			responseEntity=new ResponseEntity<ResponseStructure<List<Customer>>>(responseStructure,HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer, int c_id, int b_id, int h_id) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer customer1 = customerDao.getCustomerById(c_id);
		if (customer1 != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(customerDao.updateCustomer(customer,c_id, b_id, h_id));
		} else {
			throw new InvalidIdException("Enter Customer "+c_id+" Id Is Invalid ");

		}
		return new ResponseEntity<ResponseStructure<Customer>>(responseStructure,HttpStatus.OK);

	}
	public ResponseEntity<ResponseStructure<String>> deleteCustomerById(int c_id){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		if(customerDao.getCustomerById(c_id)!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(customerDao.deleteCustomer(c_id));
		}else {
			throw new InvalidIdException("enter Customer "+c_id +" Id Is Invalid");

		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Customer>>  validateCustomerLogIn(String email,String password) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Customer Customer1 = customerDao.validateCustomerLogIn(email,password);
		if (Customer1 != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(Customer1);
		} else {
			throw new EnterPasswordOrEmailIsWorgException("Enter Email Or Password Wrong");
		}
		return new ResponseEntity<ResponseStructure<Customer>>(responseStructure,HttpStatus.OK);

	}
}

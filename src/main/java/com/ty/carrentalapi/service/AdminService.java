package com.ty.carrentalapi.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.carrentalapi.dao.AdminDao;
import com.ty.carrentalapi.dao.BranchDao;
import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.Branch;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.exception.EnterPasswordOrEmailIsWorgException;
import com.ty.carrentalapi.exception.InvalidIdException;
import com.ty.carrentalapi.exception.NoDataFoundException;

@Service
public class AdminService {
	@Autowired
	AdminDao adminDao;
	@Autowired
	BranchDao branchDao;
	

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin, int b_id) {	
		 ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		if(branchDao.getBranchbyId(b_id)!=null) {
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("success");
		responseStructure.setData(adminDao.saveAdmin(admin, b_id));	
		}else {
			throw new InvalidIdException("Enter Branch Id Is Wrong");
		}
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>>  getAdminById(int a_id) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin = adminDao.getAdminById(a_id);
		if (admin != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(admin);
		} else {
			throw new InvalidIdException("Enter Admin Id Is wrong");
		}
		return new ResponseEntity<ResponseStructure<Admin>> (responseStructure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Admin>>>   findAllAdmin() {
		ResponseEntity<ResponseStructure<List<Admin>>> responseEntity=null;
		ResponseStructure<List<Admin>> responseStructure = new ResponseStructure<List<Admin>>();
		List<Admin> admins = adminDao.getAllAdmin();
		if (admins != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(admins);
			responseEntity= new ResponseEntity<ResponseStructure<List<Admin>>> (responseStructure,HttpStatus.OK);

		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(" not success");
			responseStructure.setData(null);
			 responseEntity= new ResponseEntity<ResponseStructure<List<Admin>>> (responseStructure,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Admin>>  updateAdmin(Admin admin, int a_id) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin1 = adminDao.getAdminById(a_id);
		if(adminDao.getAdminById(a_id)!=null) {
			if (admin1 != null) {
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("success");
				responseStructure.setData(adminDao.updateAdmin(admin, a_id));
			} else {
				throw new NoDataFoundException("no data to show");
			}
		}else {
			throw new InvalidIdException("Enter Admin Id Wrong");
		}
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Admin>>  validateAdminLogIn(String email,String password) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin1 = adminDao.validateAdminLogIn(email,password);
		if (admin1 != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(admin1);
		} else {
			throw new EnterPasswordOrEmailIsWorgException("Enter Email Or Password Wrong");
		}
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.OK);

	}
	public ResponseEntity<ResponseStructure<String>>  deleteAdmin(int a_id) {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		if(getAdminById(a_id)!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(adminDao.deleteAdmin(a_id));
		}else {
			throw new InvalidIdException("Enter Admin Id Is Wrong");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	}
}

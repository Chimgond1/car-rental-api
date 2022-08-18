package com.ty.carrentalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.carrentalapi.dao.AdminDao;
import com.ty.carrentalapi.dao.BranchDao;
import com.ty.carrentalapi.dao.HeadOfficeDao;
import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.Branch;
import com.ty.carrentalapi.dto.HeadOffice;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.exception.InvalidIdException;
import com.ty.carrentalapi.repository.BranchRepository;

@Service
public class BranchService {
	@Autowired
	BranchDao branchDao;
	@Autowired
	HeadOfficeDao headOfficeDao;
	@Autowired
	AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Branch>> savaBranch(Branch branch, int h_id) {
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("success");
		responseStructure.setData(branchDao.saveBranch(branch, h_id));
		return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Branch>> getBranchById(int b_id) {
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		Branch branch = branchDao.getBranchbyId(b_id);
		if (branch != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(branch);

		} else {
			throw new InvalidIdException(" given Branch " + b_id + " is invalid");
		}
		return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		ResponseEntity<ResponseStructure<List<Branch>>> responseEntity = null;
		ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
		List<Branch> branchs = branchDao.findAllBranch();
		if (branchs != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(branchs);
			responseEntity = new ResponseEntity<ResponseStructure<List<Branch>>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(" there is no branches");
			responseStructure.setData(null);
			responseEntity = new ResponseEntity<ResponseStructure<List<Branch>>>(responseStructure,
					HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch branch, int b_id, int h_id) {
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
//		Branch branch2 = branchDao.getBranchbyId(b_id);
//		HeadOffice headOffice = headOfficeDao.getHeadOfficeById(h_id);

		if (branchDao.getBranchbyId(b_id)!= null ) {
			if(headOfficeDao.getHeadOfficeById(h_id)!=null) {
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("success");
				responseStructure.setData(branchDao.updateBranch(branch, b_id, h_id));
			} else {
				throw new InvalidIdException("Enter  head office  Id Is Invalid");
			}	
		}else {
			throw new InvalidIdException("Entered Branch Id Is Wrong");
		}
		return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);

	
	}
	public ResponseEntity<ResponseStructure<String>>  deleteBranch(int b_id){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		Branch branch=branchDao.getBranchbyId(b_id);
		if(branchDao.getBranchbyId(b_id)!=null) {
			List<Admin> list=adminDao.getAllAdmin();
			for (Admin admin : list) {
				if(b_id==admin.getBranch().getB_id()) {
					admin.setBranch(null);
				}responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("success");
				responseStructure.setData(branchDao.deleteBranch(b_id));

			}
			
			}else {
			throw new InvalidIdException("Enter Branch Id Is Invalid");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);

	 }

}




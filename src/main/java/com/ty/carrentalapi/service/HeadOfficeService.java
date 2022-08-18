package com.ty.carrentalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.carrentalapi.dao.BranchDao;
import com.ty.carrentalapi.dao.HeadOfficeDao;
import com.ty.carrentalapi.dao.OwnerDao;
import com.ty.carrentalapi.dto.Branch;
import com.ty.carrentalapi.dto.HeadOffice;
import com.ty.carrentalapi.dto.Owner;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.exception.EnterNameWrongException;
import com.ty.carrentalapi.exception.InvalidIdException;
import com.ty.carrentalapi.exception.NoDataFoundException;

import net.bytebuddy.asm.Advice.Return;

@Service
public class HeadOfficeService {
	@Autowired
	HeadOfficeDao headOfficeDao;
	@Autowired
	OwnerDao ownerDao;
	@Autowired
	BranchDao branchDao;

	public ResponseEntity<ResponseStructure<HeadOffice>> saveHeadOffice(HeadOffice headOffice, int o_id) {

		ResponseStructure<HeadOffice> responseStructure = new ResponseStructure<HeadOffice>();
		Owner owner = ownerDao.getOwner(o_id);
		if (owner != null) {
			if (headOffice != null) {
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("success");
				responseStructure.setData(headOfficeDao.saveHeadOffice(headOffice, o_id));

			} else {
				throw new NoDataFoundException("enter Headoffice Data Is Wrong");
			}
		}else {
			throw new InvalidIdException("enter Owner Id Is Wrong");

		}
		return new ResponseEntity<ResponseStructure<HeadOffice>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<HeadOffice>> getHeadOfficeById(int h_id) {
		ResponseStructure<HeadOffice> responseStructure = new ResponseStructure<HeadOffice>();
		HeadOffice headOffice = headOfficeDao.getHeadOfficeById(h_id);
		if (headOffice != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(headOffice);

		} else {
			throw new InvalidIdException("Enter Headoffice Id Is Wrong");
		}
		return new ResponseEntity<ResponseStructure<HeadOffice>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<HeadOffice>>> getAllHeadOffice() {
		ResponseStructure<List<HeadOffice>> responseStructure = new ResponseStructure<List<HeadOffice>>();
		List<HeadOffice> list = headOfficeDao.getAllHeadOffice();
		if (list != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(list);
		} else {
			throw new NoDataFoundException("There Is No Headoffice To Display");
		}
		return new ResponseEntity<ResponseStructure<List<HeadOffice>>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteHeadOffice(int h_id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if( headOfficeDao.getHeadOfficeById(h_id)!=null) {
			List<Branch> branchs=branchDao.findAllBranch();
			for (Branch branch : branchs) {
				if(h_id==branch.getHeadOffice().getH_id()) {
					branch.setHeadOffice(null);
				}
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("success");
				responseStructure.setData(headOfficeDao.deleteHeadOffice(h_id));
			
			}
			}		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<HeadOffice>> updateHeadOffice(int h_id, HeadOffice headOffice) {
		ResponseStructure<HeadOffice> responseStructure = new ResponseStructure<HeadOffice>();
		HeadOffice headOffice2 = headOfficeDao.getHeadOfficeById(h_id);
		if (headOffice2 != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(headOfficeDao.updateHeadOffice(h_id, headOffice));
		} else {
			throw new InvalidIdException("Enter Headoffice Id Is wrong");
		}
		return new ResponseEntity<ResponseStructure<HeadOffice>>(responseStructure, HttpStatus.OK);
	}
}

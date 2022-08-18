package com.ty.carrentalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.carrentalapi.dao.OwnerDao;
import com.ty.carrentalapi.dto.Owner;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.exception.EnterPasswordOrEmailIsWorgException;
import com.ty.carrentalapi.exception.InvalidIdException;
import com.ty.carrentalapi.exception.NoDataFoundException;
@Service
public class OwnerService {
	@Autowired
	OwnerDao ownerDao;
	public  ResponseEntity<ResponseStructure<Owner>>  saveOwner(Owner owner) {
		ResponseStructure<Owner> responseStructure=new  ResponseStructure<Owner>();
		
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("success");
		responseStructure.setData(ownerDao.saveOwner(owner));
		
		return new ResponseEntity<ResponseStructure<Owner>>(responseStructure,HttpStatus.CREATED);

		}
	public ResponseEntity<ResponseStructure<Owner>> getOwner(int o_id) {
		ResponseStructure<Owner> responseStructure=new  ResponseStructure<Owner>();
		Owner  owner=ownerDao.getOwner(o_id);
		if(owner!=null) {
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("success");
		responseStructure.setData(owner);
		
		}else {
			throw new InvalidIdException();
		}
		return new ResponseEntity<ResponseStructure<Owner>>(responseStructure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Owner>>> getAllOwner() {
		ResponseStructure<List<Owner>> responseStructure=new  ResponseStructure<List<Owner>>();
		  List<Owner> list=ownerDao.getAllOwner();
		if(list!=null) {
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("success");
		responseStructure.setData(list);
		
		}else {
			throw new NoDataFoundException();
		}
		return new ResponseEntity<ResponseStructure<List<Owner>>>(responseStructure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Owner>> validateOwner(String email,String password) {
		ResponseStructure<Owner> responseStructure=new  ResponseStructure<Owner>();
		Owner owner=ownerDao.validateOwner(email, password);
		if(owner!=null) {
			double rNo=Math.random();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("success");
		responseStructure.setData(owner);
		
	}else {
		throw new EnterPasswordOrEmailIsWorgException();
	}
		return new ResponseEntity<ResponseStructure<Owner>>(responseStructure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<String>> deleteOwner(int o_id){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
       String a=ownerDao.deleteOwner(o_id);
      Owner owner =ownerDao.getOwner(o_id);
       if(a!=null && owner!=null) {
    	   responseStructure.setStatusCode(HttpStatus.OK.value());
   		responseStructure.setMessage("success");
   		responseStructure.setData(a);  
       }else {
    	   throw new InvalidIdException();
       }
       
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK); 
	}
	public  ResponseEntity<ResponseStructure<Owner>>  updateOwner(Owner owner,int o_id) {
		ResponseStructure<Owner> responseStructure=new ResponseStructure<Owner>();
         Owner owner2=ownerDao.getOwner(o_id);
         if(owner2!=null) {
        	 responseStructure.setStatusCode(HttpStatus.OK.value());
        	 responseStructure.setMessage("success");
        	responseStructure.setData(ownerDao.updateOwner(owner, o_id));
        	 }else {
        		 throw new InvalidIdException();
        	 }
         return new ResponseEntity<ResponseStructure<Owner>>(responseStructure,HttpStatus.OK);
	}

}

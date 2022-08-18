package com.ty.carrentalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.carrentalapi.dto.Owner;
import com.ty.carrentalapi.repository.OwnerRepository;

@Repository
public class OwnerDao {
	@Autowired
	OwnerRepository ownerRepository;

	public Owner saveOwner(Owner owner) {

		return ownerRepository.save(owner);
	}

	public Owner getOwner(int o_id) {

		Optional<Owner> optional = ownerRepository.findById(o_id);
		if (optional.isEmpty()) {
			return null;
		} else {
			double randomNo=Math.random();
			return optional.get();
			
		}
	}
	public Owner validateOwner(String email,String password) {
		return ownerRepository.validateOwner(email, password);
	}
	public String deleteOwner(int o_id){
		Optional<Owner> owner=ownerRepository.findById(o_id);
		if(owner.isEmpty()) {
			return "empty";
		}else {
			ownerRepository.delete(owner.get());
			return "deleted";
		}
		
	}
	public List<Owner> getAllOwner(){
		return ownerRepository.findAll();
		
	}
	public Owner updateOwner(Owner owner,int o_id){
		Owner owner2=getOwner(o_id);
		if(owner.getOwnerId()!=0) {
			owner2.setOwnerId(owner.getOwnerId());
		}if(owner.getOwnerName()!=null) {
			owner2.setOwnerName(owner.getOwnerName());
		}if(owner.getEmail()!=null) {
			owner2.setEmail(owner.getEmail());
		}if(owner.getPassword()!=null) {
			owner2.setPassword(owner.getPassword());
		}
		return ownerRepository.save(owner2);
	}
	///////////////////////////////////////////////////////////////////
	public  static double random() {
		double run=Math.random();
		return run;
	}
}

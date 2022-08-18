package com.ty.carrentalapi.dao;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.carrentalapi.dto.HeadOffice;
import com.ty.carrentalapi.dto.Owner;
import com.ty.carrentalapi.repository.HeadOfficeRepository;
import com.ty.carrentalapi.repository.OwnerRepository;

@Repository
public class HeadOfficeDao {
	@Autowired
	HeadOfficeRepository headOfficeRepository;
	@Autowired
	OwnerDao ownerDao;

	public HeadOffice saveHeadOffice(HeadOffice headOffice,int o_id) {
		Owner owner=ownerDao.getOwner(o_id);
		if(owner!=null) {
		headOffice.setOwner(owner);	
		return headOfficeRepository.save(headOffice);
		}else {
			return null;
		}
		
	}

	public HeadOffice getHeadOfficeById(int h_id) {
		Optional<HeadOffice>optional= headOfficeRepository.findById(h_id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}

	}
	public List<HeadOffice> getAllHeadOffice(){
		return headOfficeRepository.findAll();
	}
	
	
	public String deleteHeadOffice(int h_id){
		Optional<HeadOffice> optional=headOfficeRepository.findById(h_id);
		if(optional.isEmpty()) {
			return "Empty";
		}else {
			HeadOffice office = getHeadOfficeById(h_id);
			office.setOwner(null);
			headOfficeRepository.delete(optional.get());	
			return "deleted";
		}
	}

	
	public HeadOffice updateHeadOffice(int h_id,HeadOffice headOffice){
           HeadOffice headOffice2=getHeadOfficeById(h_id);
            if(headOffice.getH_id()!=0) {
            	headOffice2.setH_id(headOffice.getH_id());
            }
            if(headOffice.getH_name()!=null) {
            	headOffice2.setH_name(headOffice.getH_name());
            }
            if(headOffice.getH_website()!=null) {
            	headOffice2.setH_website(headOffice.getH_website());
            }
//            if(headOffice.getOwner().getOwnerId()!=0) {
//            	
//            }
            return headOfficeRepository.save(headOffice2);
	}
	

}

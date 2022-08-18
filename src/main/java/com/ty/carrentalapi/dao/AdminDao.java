package com.ty.carrentalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.Branch;
import com.ty.carrentalapi.repository.AdminRepository;

@Repository
public class AdminDao {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	BranchDao branchDao;
	@Autowired
	OwnerDao ownerDao;
	
	

	public Admin saveAdmin(Admin admin, int b_id) {
		if(branchDao.getBranchbyId(b_id)!=null) {
		admin.setBranch(branchDao.getBranchbyId(b_id));
		return adminRepository.save(admin);
		}else {
			return null;
		}
	}

	public Admin getAdminById(int a_id) {
		Optional<Admin>  optional=adminRepository.findById(a_id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}

	}

	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}

	public String deleteAdmin(int a_id) {
		Optional<Admin> optional = adminRepository.findById(a_id);
		if (optional.isEmpty()) {
			return "empty";
		} else {
			adminRepository.delete(optional.get());
			return "deleted";
		}
	}

	public Admin updateAdmin(Admin admin, int a_id) {
		Admin admin2=getAdminById(a_id);//database one admin
		if(admin2!=null) {
			if(admin.getA_id()>0) {
				admin2.setA_id(admin.getA_id());
			}else {
				admin2.setA_id(admin2.getA_id());
			}
			if(admin.getA_name()!=null) {
				admin2.setA_name(admin.getA_name());
			}else {
				admin2.setA_name(admin2.getA_name()); ;
			}
			if(admin.getA_phone()>0) {
				admin2.setA_phone(admin.getA_phone());
			}else{
				admin2.setA_phone(admin2.getA_phone());
			}
			if(admin.getEmail()!=null) {
				admin2.setEmail(admin.getEmail());
			}else {
				admin2.setEmail(admin2.getEmail());
			}if(admin.getPassword()!=null) {
				admin2.setPassword(admin.getPassword());
			}else {
				admin2.setPassword(admin2.getPassword());
			}
		}return adminRepository.save(admin2);
	}
//	admin2.getA_id();	
//	
//	admin2.setA_name(admin.getA_name());             
//	                            
//	admin2.getA_phone();                
//	admin2.getBranch();             
//	admin2.getEmail();        		
//	admin2.getPassword();                        
//		
//		
//		
//	admin.getA_id(); 
//	 admin.getA_phone(); 
//	 admin.getBranch();
//	 admin.getEmail();
//	 admin.getPassword();  
		
//		Optional<Admin> optional = adminRepository.findById(a_id);
//		if (optional.isEmpty()) {
//			return null;
//		} else {
//			optional.get().setA_id(admin.getA_id());
//			optional.get().setA_name(admin.getA_name());
//			Admin admin1=optional.get();
//			Branch branch=admin1.getBranch();
//			admin.setBranch(branch);
//			
			
		
	
	public Admin validateAdminLogIn(String email,String password) {
		return adminRepository.validateAdminLogIn(email, password);
	}

}

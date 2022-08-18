package com.ty.carrentalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.carrentalapi.dto.Branch;
import com.ty.carrentalapi.dto.HeadOffice;
import com.ty.carrentalapi.repository.BranchRepository;

@Repository
public class BranchDao {
	@Autowired
	BranchRepository branchRepository;
	@Autowired
	HeadOfficeDao headOfficeDao;
	
	public  Branch saveBranch(Branch branch,int h_id){
		HeadOffice headOffice=headOfficeDao.getHeadOfficeById(h_id);
		if(headOffice!=null) {
		branch.setHeadOffice(headOfficeDao.getHeadOfficeById(h_id));
		return branchRepository.save(branch);
		}else {
			return null;
		}
	}
	public Branch getBranchbyId(int b_id){
		Optional<Branch> optional=branchRepository.findById(b_id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
		
	}
	
	public String deleteBranch(int b_id){
		Optional<Branch> optional=branchRepository.findById(b_id);
		if(optional.isEmpty()) {
			return "Empty";
		}else {
			
			
			branchRepository.delete(optional.get());
			return "deleted";
		}
		
		
		}
	
	public Branch updateBranch(Branch branch,int b_id,int h_id) {
	Branch branch2=getBranchbyId(b_id);
	HeadOffice headOffice=headOfficeDao.getHeadOfficeById(h_id);
	if(branch2!=null&&headOffice!=null) {
		branch.setHeadOffice(headOfficeDao.getHeadOfficeById(h_id));
		branch.setB_id(b_id);
		return branchRepository.save(branch);
	}else {
		return null;
	}
		
	}
	public List<Branch> findAllBranch(){
	return	branchRepository.findAll();
	}

}

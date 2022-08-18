package com.ty.carrentalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.carrentalapi.dto.Admin;
import com.ty.carrentalapi.dto.Customer;
import com.ty.carrentalapi.repository.CustomerRepository;

@Repository
public class CustomerDao {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	BranchDao branchDao;
	@Autowired
	VehicleDao vehicleDao;
	
	public Customer saveCustomer(Customer customer,int b_id,int v_id){
		customer.setBranch(branchDao.getBranchbyId(b_id));
		customer.setVehicle(vehicleDao.getVehicleById(v_id));
		return customerRepository.save(customer);
		
	}
	public Customer getCustomerById(int c_id){
		Optional<Customer> optional=customerRepository.findById(c_id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
		
	}
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
     public Customer updateCustomer(Customer customer,int c_id,int b_id,int h_id){
    	Customer customer2=getCustomerById(c_id);
    	if(customer.getC_id()!=0) {
    		customer2.setC_id(customer.getC_id());
    	}if(customer.getC_name()!=null) {
    		customer2.setC_name(customer.getC_name());
    	}if(customer.getGender()!=null) {
    		customer2.setGender(customer.getGender());
    	}if(customer.getPassword()!=null) {
    		customer2.setPassword(customer.getPassword());
    	}if(customer.getC_email()!=null) {
    		customer2.setC_email(customer.getC_email());
    	}if(customer.getAddress()!=null) {
    		customer2.setAddress(customer.getAddress());
    	}if(customer.getPhone()!=0) {
    		customer2.setPhone(customer.getPhone());
    	}
    	customerRepository.save(customer2);
    	return customer2;
    }
    public String deleteCustomer(int c_id){
    	Optional<Customer>optional=customerRepository.findById(c_id);
    	if(optional.isEmpty()) {
    		return "Empty";
    	}else {
    		customerRepository.delete(optional.get());
    		return "Deleted";
    	}
    }
    public Customer validateCustomerLogIn(String email,String password) {
		return customerRepository.validateCustomerLogIn(email, password);
	}

}

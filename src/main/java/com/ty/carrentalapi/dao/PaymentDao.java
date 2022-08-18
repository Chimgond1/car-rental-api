package com.ty.carrentalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.carrentalapi.dto.Booking;
import com.ty.carrentalapi.dto.Payment;
import com.ty.carrentalapi.repository.PaymentRepository;

@Repository
public class PaymentDao {
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	VehicleDao vehicleDao;
	@Autowired
	BookingDao bookingDao;
	
	public Payment savePayment(Payment payment,int c_id,int v_id,int b_id){
		payment.setCustomer(customerDao.getCustomerById(c_id));
		payment.setVehicle(vehicleDao.getVehicleById(b_id));
		payment.setBooking(bookingDao.getBookingByById(b_id));
		return paymentRepository.save(payment);
	}
	
	public Payment getPaymentById(int p_id){
		return paymentRepository.findById(p_id).get();
	}
	public List<Payment> getAllPayment(){
		return paymentRepository.findAll();
	}
	public boolean deletePayment(int p_id){
		Optional<Payment>optional=paymentRepository.findById(p_id);
		if(optional.isEmpty()) {
			return false;
		}else {
			paymentRepository.delete(optional.get());
			return true;
		}
	}
	public Payment updatePayment(Payment payment,int p_id){
		Optional<Payment>optional=paymentRepository.findById(p_id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return paymentRepository.save(payment);
		}
	}

}

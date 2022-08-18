package com.ty.carrentalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.carrentalapi.dao.PaymentDao;
import com.ty.carrentalapi.dto.Booking;
import com.ty.carrentalapi.dto.Payment;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.exception.InvalidIdException;

@Service
public class PaymentService {
	@Autowired
	PaymentDao paymentDao;

	public ResponseEntity<ResponseStructure<Payment>>  savePayment(Payment payment, int c_id, int v_id, int b_id) {
		ResponseStructure<Payment> responseStructure = new ResponseStructure<Payment>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("success");

		responseStructure.setData(paymentDao.savePayment(payment, c_id, v_id, b_id));
		return new ResponseEntity<ResponseStructure<Payment>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(int p_id) {
		ResponseStructure<Payment> responseStructure = new ResponseStructure<Payment>();
		Payment payment = paymentDao.getPaymentById(p_id);
		if (payment != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(payment);
		} else {
			throw new InvalidIdException("Enter "+p_id+" Id Is Invalid ");

		}
		return new ResponseEntity<ResponseStructure<Payment>>(responseStructure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> findAllPayment() {
		ResponseEntity<ResponseStructure<List<Payment>>>  responseEntity=null;
		ResponseStructure<List<Payment>> responseStructure = new ResponseStructure<List<Payment>>();
		List<Payment> payments = paymentDao.getAllPayment();
		if (payments != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(payments);
			responseEntity=new ResponseEntity<ResponseStructure<List<Payment>>>(responseStructure,HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(" not success");
			responseStructure.setData(null);
			responseEntity=new ResponseEntity<ResponseStructure<List<Payment>>>(responseStructure,HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Payment>> updateBooking(Payment payment, int p_id, int c_id, int v_id, int b_id) {
		ResponseStructure<Payment> responseStructure = new ResponseStructure<Payment>();
		Payment payment2 = paymentDao.getPaymentById(p_id);
		if (payment2 != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(paymentDao.savePayment(payment, c_id, v_id, b_id));
		} else {
			throw new InvalidIdException("Enter "+p_id+" Id Is Invalid ");

		}
		return new ResponseEntity<ResponseStructure<Payment>>(responseStructure,HttpStatus.OK);

	}

}

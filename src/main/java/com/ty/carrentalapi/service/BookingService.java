package com.ty.carrentalapi.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.carrentalapi.dao.BookingDao;
import com.ty.carrentalapi.dao.CustomerDao;
import com.ty.carrentalapi.dao.VehicleDao;
import com.ty.carrentalapi.dto.Booking;
import com.ty.carrentalapi.dto.Customer;
import com.ty.carrentalapi.dto.ResponseStructure;
import com.ty.carrentalapi.dto.Vehicle;
import com.ty.carrentalapi.exception.EnterNameWrongException;
import com.ty.carrentalapi.exception.InvalidIdException;

@Service
public class BookingService {
	@Autowired
	BookingDao bookingDao;
	@Autowired
	VehicleDao vehicleDao;
	@Autowired
	CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Booking>>   saveBooking(Booking booking, int v_id, int c_id) {
		ResponseStructure<Booking> responseStructure = new ResponseStructure<Booking>();
		Vehicle findVehicle = vehicleDao.getVehicleById(v_id);
		Customer findCustomer = customerDao.getCustomerById(c_id);
		
			if (findCustomer != null) {
				
					if (findVehicle != null) {
						for (Booking book : bookingDao.getAllBooking()) {
							if (booking.getStartTripDate().toString().equals(book.getStartTripDate().toString())
									&& booking.getEndTripDate().toString().equals(book.getEndTripDate().toString())) {
								if (booking.getStartTime().isAfter(book.getStartTime())
										&& booking.getEndTime().isAfter(book.getEndTime())) {
									responseStructure.setStatusCode(HttpStatus.CREATED.value());
									responseStructure.setMessage("success1as");
									responseStructure.setData(bookingDao.saveBooking(booking, v_id, c_id));

								} else if (booking.getStartTime().isBefore(book.getStartTime())
										&& booking.getEndTime().isBefore(book.getStartTime())) {
									responseStructure.setStatusCode(HttpStatus.CREATED.value());
									responseStructure.setMessage("success1df");
									responseStructure.setData(bookingDao.saveBooking(booking, v_id, c_id));

								} else {
									throw new EnterNameWrongException();
								}

							} else {
								responseStructure.setStatusCode(HttpStatus.CREATED.value());
								responseStructure.setMessage("success1dfghj");
								responseStructure.setData(bookingDao.saveBooking(booking, v_id, c_id));

							}
						}responseStructure.setStatusCode(HttpStatus.CREATED.value());
						responseStructure.setMessage("success1dfghj345678");
						responseStructure.setData(bookingDao.saveBooking(booking, v_id, c_id));

					} else {
						responseStructure.setMessage("vehicle not found exception");

					}
				
			} else {
				responseStructure.setMessage("customer not found exception ");
			}
		
		return new ResponseEntity<ResponseStructure<Booking>> (responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Booking>>  getBookingById(int b_id) {
		ResponseStructure<Booking> responseStructure = new ResponseStructure<Booking>();
		Booking booking = bookingDao.getBookingByById(b_id);
		if (bookingDao.getBookingByById(b_id)!= null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(booking);
		} else {
			throw new InvalidIdException("Enter Booking " + b_id + " Id Is Invalid ");

		}
		return new ResponseEntity<ResponseStructure<Booking>>(responseStructure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> findAllBooking() {
		ResponseEntity<ResponseStructure<List<Booking>>> responseEntity=null;
		ResponseStructure<List<Booking>> responseStructure = new ResponseStructure<List<Booking>>();
		List<Booking> bookings = bookingDao.getAllBooking();
		if (bookings != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(bookings);
			responseEntity=new ResponseEntity<ResponseStructure<List<Booking>>>(responseStructure,HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(" not success");
			responseStructure.setData(null);
			responseEntity=new ResponseEntity<ResponseStructure<List<Booking>>>(responseStructure,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
		
	}

	public ResponseEntity<ResponseStructure<Booking>> updateBooking(Booking booking, int b_id, int v_id, int c_id) {
		ResponseStructure<Booking> responseStructure = new ResponseStructure<Booking>();
		Booking booking2 = bookingDao.getBookingByById(b_id);
		if (bookingDao.getBookingByById(b_id) != null) {
			if(vehicleDao.getVehicleById(v_id)!=null) {
				if(customerDao.getCustomerById(c_id)!=null) {
					responseStructure.setStatusCode(HttpStatus.OK.value());
					responseStructure.setMessage("success");
					responseStructure.setData(bookingDao.updateBooking(booking,b_id, v_id, c_id));

				}else {
					throw new InvalidIdException("Enter Customer " + c_id + " Id Is Invalid ");

				}
			}else {
				throw new InvalidIdException("Enter Vehicle " + v_id + " Id Is Invalid ");

			}
			} else {
			throw new InvalidIdException("Enter Booking " + b_id + " Id Is Invalid ");

		}
		return new ResponseEntity<ResponseStructure<Booking>>(responseStructure,HttpStatus.OK);

	}
	
	public ResponseEntity<ResponseStructure<String>>  deletBooking(int b_id) {
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		if(getBookingById(b_id)!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(bookingDao.deleteBooking(b_id));
		}else {
			throw new InvalidIdException("Enter Booking Id Is Wrong");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
	}

}



package com.ty.carrentalapi.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.carrentalapi.dto.Booking;
import com.ty.carrentalapi.dto.Vehicle;
import com.ty.carrentalapi.repository.BookingRepository;

@Repository
public class BookingDao {
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	VehicleDao vehicleDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	BranchDao branchDao;

	public Booking saveBooking(Booking booking, int v_id, int c_id) {
		if(customerDao.getCustomerById(c_id)!=null) {
			if(vehicleDao.getVehicleById(v_id)!=null) {
				booking.setVehicle(vehicleDao.getVehicleById(v_id));
				booking.setCustomer(customerDao.getCustomerById(c_id));
				return bookingRepository.save(booking);
				
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	
	
	public Booking getBookingByById(int b_id) {
		if(branchDao.getBranchbyId(b_id)!=null) {
		return bookingRepository.findById(b_id).get();
		}else {
			return null;
		}
	}

	public List<Booking> getAllBooking() {
		return bookingRepository.findAll();
	}

	public String deleteBooking(int b_id) {
		Optional<Booking> optional = bookingRepository.findById(b_id);
		if (optional.isEmpty()) {
			return "Empty";
		} else {
			bookingRepository.delete(optional.get());
			return "Deleted";
		}
	}

	public Booking updateBooking(Booking booking, int b_id,int v_id,int c_id) {
		Booking booking2=getBookingByById(b_id);
		if(booking.getBooking_id()!=0) {
			booking2.setBooking_id(booking.getBooking_id());
		}if(booking.getStartTripDate()!=null) {
			booking2.setStartTripDate(booking.getStartTripDate());
		}if(booking.getEndTripDate()!=null) {
			booking2.setEndTripDate(booking.getEndTripDate());
		}if(booking.getStartTime()!=null) {
			booking2.setStartTime(booking.getStartTime());
		}if(booking.getEndTime()!=null) {
			booking2.setEndTime(booking.getEndTime());
		}
		bookingRepository.save(booking2);
		return booking2;
		
	}

	//////////////////////////////////////////////////////
	public LocalDate bookingStartDate(int v_id) {
		Vehicle vehicle = vehicleDao.getVehicleById(v_id);
		LocalDate startDate ;
		if (vehicle != null) {
			startDate = ((Booking) vehicle.getBooking()).getStartTripDate();

		}else {
			return null;
		}
		return startDate;
	}
	public LocalDate bookingEndDate(int v_id) {
		Vehicle vehicle = vehicleDao.getVehicleById(v_id);
		LocalDate endDate ;
		if (vehicle != null) {
			endDate = ((Booking) vehicle.getBooking()).getEndTripDate();

		}else {
			return null;
		}
		return endDate;
	}
	public LocalTime bookingStartTime(int v_id) {
		Vehicle vehicle = vehicleDao.getVehicleById(v_id);
		LocalTime startTime = null;
		if (vehicle != null) {
			startTime = ((Booking) vehicle.getBooking()).getStartTime();

		}
		return startTime;
	}
	public LocalTime bookingEndTime(int v_id) {
		Vehicle vehicle = vehicleDao.getVehicleById(v_id);
		LocalTime endTime = null;
		if (vehicle != null) {
			endTime = ((Booking) vehicle.getBooking()).getEndTime();

		}
		return endTime;
	}
}
package com.ty.carrentalapi.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int booking_id;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate startTripDate;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime startTime;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private  LocalDate endTripDate;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime endTime;
	
	//
	@JsonIgnore
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn
	private Vehicle vehicle;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	 private Customer customer;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
	private List<Payment> payment;
}

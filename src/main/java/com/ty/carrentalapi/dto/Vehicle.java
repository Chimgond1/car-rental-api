package com.ty.carrentalapi.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String vehicle_no;
	private String vehicle_type;
	private String vehicle_model;
	private double vehicle_rent_price;
	//
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Admin admin;
	//
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Branch branch;
	//
	@JsonIgnore
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)	
	private List<Customer> customer;
	//
	@JsonIgnore
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
	private List<Booking> booking;
	
	
	//////////////
	@JsonIgnore
	@OneToMany(mappedBy = "vehicle",cascade = CascadeType.ALL)
	private List<Payment> payment;

}

package com.ty.carrentalapi.dto;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int b_id;
	@NotBlank(message = "branch name should not be blank")
	private String b_name;
	@NotBlank(message = "should not be blank")
	//@Pattern(regexp = "", message = "website should be www.example.com")
	private String b_website;
	//
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn
	private HeadOffice headOffice;
	//
	@JsonIgnore
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
	private List<Admin> admin;
	//
	@JsonIgnore
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
	private List<Vehicle> vehicles;
	//
	@JsonIgnore
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
	private List<Customer> customer;

}

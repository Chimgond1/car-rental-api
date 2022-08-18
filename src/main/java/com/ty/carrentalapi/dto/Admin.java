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
import javax.persistence.Transient;
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
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int a_id;
	@NotBlank(message="name should not be blank")
	private String  a_name;
	@NotBlank(message="email should not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-z"+"A-Z]{2,7}$",message="email should be EXAMPLE abc@gmail.com")
	private String email;
	//@NotBlank(message="password should not be blank")
	private String password;
	
	private long a_phone;
	@Transient
	private double inputzzzzz;
	
	//
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	@JsonIgnore
	private Branch  branch;
	
	@JsonIgnore
	@OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
	private List<Vehicle>  vehicle;
	

}

package com.ty.carrentalapi.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

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
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ownerId;
	@NotBlank(message = "Name should not blank")
	private String OwnerName;
	@NotBlank(message = "Email should not blank")
	private String email;
	@NotBlank(message = "Password should not blank")
	private String password;
	
	
	
	@JsonIgnore
	@OneToOne(mappedBy = "owner",cascade = CascadeType.ALL)
	private HeadOffice headOffice;

}

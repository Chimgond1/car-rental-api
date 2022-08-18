package com.ty.carrentalapi.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeadOffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int h_id;
	@NotBlank(message =" head office name should not be blank" )
	private String h_name;
	@NotBlank(message = "head office website should not be blank")
	private String h_website;
	
	@JsonIgnore
	@OneToMany(mappedBy = "headOffice",cascade = CascadeType.ALL)
	private List<Branch> branchs;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Owner owner;
}

package com.ty.carrentalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure<T> {
	private int statusCode;
	private String message;
	private T data;
	

}

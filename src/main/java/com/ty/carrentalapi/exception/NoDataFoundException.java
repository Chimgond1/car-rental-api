package com.ty.carrentalapi.exception;

public class NoDataFoundException extends RuntimeException{

	
	String message=" No Data found";
	public NoDataFoundException() {
		
	}
	public NoDataFoundException(String message) {
		
		this.message = message;
	}
	@Override
	public String getMessage() {
		
		return message;
	}
	
}

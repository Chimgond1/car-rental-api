package com.ty.carrentalapi.exception;

public class InvalidIdException extends RuntimeException {
	
	String message="Enter Id Is Invalid ";
	
	public InvalidIdException() {
		
	}

	public InvalidIdException(String message) {
		
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}

}

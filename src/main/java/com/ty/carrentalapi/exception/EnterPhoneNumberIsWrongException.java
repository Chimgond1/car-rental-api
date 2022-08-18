package com.ty.carrentalapi.exception;

public class EnterPhoneNumberIsWrongException extends RuntimeException{
	
	String message="Enter Number Is Wrong";

	public EnterPhoneNumberIsWrongException(String message) {
		
		this.message = message;
	}
	
	public EnterPhoneNumberIsWrongException() {
		
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}

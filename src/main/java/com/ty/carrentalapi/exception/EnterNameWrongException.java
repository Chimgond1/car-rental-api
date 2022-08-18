package com.ty.carrentalapi.exception;

public class EnterNameWrongException extends RuntimeException{
	
	String message="Enter Name Is Wrong or Enter Name Is Not Blank";

	public EnterNameWrongException(String message) {
		
		this.message = message;
	}
	public EnterNameWrongException() {
		
	}
	@Override
	public String getMessage() {
		return message;
	}

}

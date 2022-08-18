package com.ty.carrentalapi.exception;

public class EnterWebsiteWrongException extends RuntimeException{
	String message="Enter Website Is Wrong";
	
	public EnterWebsiteWrongException() {
		
	}

	public EnterWebsiteWrongException(String message) {
		
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}


}

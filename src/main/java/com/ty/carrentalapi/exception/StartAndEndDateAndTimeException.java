package com.ty.carrentalapi.exception;

public class StartAndEndDateAndTimeException  extends RuntimeException{
	String message="Start are End Date And Time Is Wrong";

	public StartAndEndDateAndTimeException(String message) {
		
		this.message = message;
	}
	
	public StartAndEndDateAndTimeException() {
		
	}
	@Override
	public String getMessage() {
		return message;
	}

}

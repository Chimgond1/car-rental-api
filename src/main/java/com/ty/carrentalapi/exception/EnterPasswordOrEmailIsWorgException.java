package com.ty.carrentalapi.exception;

public class EnterPasswordOrEmailIsWorgException extends RuntimeException{
	
	String message="Enter Password Or Email Is Wrong";

	public EnterPasswordOrEmailIsWorgException(String message) {
		
		this.message = message;
	}
	
	public EnterPasswordOrEmailIsWorgException() {
		
	}
    @Override
	public String getMessage() {
		return message;
	}
}

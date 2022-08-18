package com.ty.carrentalapi.exception;

import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.carrentalapi.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
//	@Bean
//	@Override
//		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//				HttpHeaders headers, HttpStatus status, WebRequest request) {
//		
//
//		Map<String, String> map=new LinkedHashMap<String, String>();
//		List<ObjectError> errors=ex.getAllErrors();
//		for(ObjectError error:errors) {
//			String fe=((FieldError) error).getField();
//			//String fieldName=fe.getField();
//			String message=error.getDefaultMessage();
//			map.put(fe, message);
//		}
//		
//		ResponseStructure<Map<String, String>> responseStructure= new ResponseStructure<Map<String,String>>();
//		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
//		responseStructure.setMessage("pojo");
//		responseStructure.setData(map);
//		
//			// TODO Auto-generated method stub
//		return new ResponseEntity<Object>(responseStructure,HttpStatus.BAD_REQUEST);
//		}
//	
	
	
	
	
	
	
	
	@ExceptionHandler(value=EnterNameWrongException.class)
 public ResponseEntity<ResponseStructure<String>> enterNameWrongException(EnterNameWrongException enwe){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Id Is Does't Exit");
		responseStructure.setData(enwe.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
	 return responseEntity;
 }
	
	
	
	
	
	
	
	
	
	
	
	
	@ExceptionHandler(value=InvalidIdException.class)
 public ResponseEntity<ResponseStructure<String>> invalidIdException(InvalidIdException iie){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Id Is Does't Exit");
		responseStructure.setData(iie.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
	 return responseEntity;
 }
	@ExceptionHandler(value = EnterPasswordOrEmailIsWorgException.class)
	public ResponseEntity<ResponseStructure<String>> enterPasswordOrEmailIsWorgException(EnterPasswordOrEmailIsWorgException epee){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Email Or Password Wrong Exception");
		responseStructure.setData(epee.getMessage());
		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	return responseEntity;
	}
	@ExceptionHandler(value = NoDataFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noDataFpoundException(NoDataFoundException nDFE){
		ResponseStructure<String> responseStructure=new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("No Data Found  Exception");
		responseStructure.setData(nDFE.getMessage());
		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	return responseEntity;
	
}
}

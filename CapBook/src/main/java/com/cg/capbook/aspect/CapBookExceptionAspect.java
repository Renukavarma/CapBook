package com.cg.capbook.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.capbook.customresponse.CustomResponse;
import com.cg.capbook.exceptions.IncorrectPasswordException;
import com.cg.capbook.exceptions.UserNotFoundException;

@ControllerAdvice(basePackages= {"com.cg.capbook.controllers"})
public class CapBookExceptionAspect {
	@ExceptionHandler({UserNotFoundException.class,IncorrectPasswordException.class})
	public ResponseEntity<CustomResponse> handelProductDetailsNotFoundException(Exception e){
		CustomResponse response=new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(),e.getMessage());
		System.out.println(response);
		return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
	}
}

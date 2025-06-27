package com.Pratham.HotelService.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handlerResorceNotFoundException(ResourceNotFoundException rnf){
		Map<String,Object> map=new HashMap<>();
		map.put("message",rnf.getMessage());
		map.put("success",true);
		map.put("status",HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
	}

}

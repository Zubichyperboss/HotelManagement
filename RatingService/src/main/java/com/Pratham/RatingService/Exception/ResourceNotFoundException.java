package com.Pratham.RatingService.Exception;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		super("REsource not found in server");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}

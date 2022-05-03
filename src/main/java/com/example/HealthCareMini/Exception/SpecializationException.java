package com.example.HealthCareMini.Exception;

public class SpecializationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	//private static final long serialVersionUID = 1L;

	public SpecializationException(){
		super();
	}
	
	public SpecializationException(String message){
		super(message);
	}

}

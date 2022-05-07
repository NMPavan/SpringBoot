package com.example.HealthCareMini.Exception;

public class DoctorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DoctorException(){
		super();
	}
	
	public DoctorException(String message){
		super(message);
	}

}

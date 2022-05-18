package com.example.HealthCareMini.Exception;

public class AppointmentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AppointmentException() {
		
	}
	
	public AppointmentException(String message) {
		super(message);
	}

}

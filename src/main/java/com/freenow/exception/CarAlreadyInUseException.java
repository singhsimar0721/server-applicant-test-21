/**
 * 
 */
package com.freenow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Class represents Car already in use exception.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Unavailable - Car already in use, carId: ")
public class CarAlreadyInUseException extends Exception {

	
	private static final long serialVersionUID = -2395704695098044185L;
	
	public CarAlreadyInUseException(String msg) {
		super(msg);
	}
	

}

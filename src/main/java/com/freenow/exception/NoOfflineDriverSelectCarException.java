/**
 * 
 */
package com.freenow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Offline user not allowed to select car exception.
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Offline driver cannot make car selection.")
public class NoOfflineDriverSelectCarException extends Exception {

	private static final long serialVersionUID = 5682715770751337123L;

	public NoOfflineDriverSelectCarException(String message) {
		super(message);
	}

}

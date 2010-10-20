package com.oschrenk.timestats.io;

/**
 * Handles the exception.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public interface ExceptionHandler {

	/**
	 * Handle the exception.
	 *
	 * @param e
	 *            the execption
	 */
	public void handle(Exception e);
}

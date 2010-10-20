package com.oschrenk.timestats.io;

/**
 * The Class StatisticsCreationException.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class StatisticsCreationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6235119846093114681L;

	/**
	 * Instantiates a new statistics creation exception.
	 *
	 * @param message
	 *            the message
	 * @param e
	 *            the original exception
	 */
	public StatisticsCreationException(final String message, final Throwable e) {
		super(message, e);
	}

}

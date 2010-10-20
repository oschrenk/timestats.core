package com.oschrenk.timestats.io;

/**
 * Rethrows every exception as a {@link RuntimeException}.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class DefaultExceptionHandler implements ExceptionHandler {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.oschrenk.timestats.io.ExceptionHandler#handle(java.lang.Exception)
	 */
	@Override
	public void handle(final Exception e) {
		throw new RuntimeException(e);
	}

}

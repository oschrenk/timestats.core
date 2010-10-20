package com.oschrenk.timestats.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Creates composite of two {@link FilenameFilter} with logical or.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public class OrCompositeFileFilter implements FileFilter {

	/** The filter a. */
	FileFilter filterA;

	/** The filter b. */
	FileFilter filterB;

	/**
	 * Instantiates a new or composite filename filter.
	 *
	 * @param filterA
	 *            the filter a
	 * @param filterB
	 *            the filter b
	 */
	public OrCompositeFileFilter(final FileFilter filterA,
			final FileFilter filterB) {
		super();
		this.filterA = filterA;
		this.filterB = filterB;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(final File path) {
		return filterA.accept(path) || filterB.accept(path);
	}

}

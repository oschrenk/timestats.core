package com.oschrenk.timestats.util;

import java.io.File;
import java.io.FileFilter;

/**
 * This filter only allows filenames with the given extension.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class ExtensionFileFilter implements FileFilter {

	/** The extension. */
	private final String dotExtension;

	/**
	 * Instantiates a new filename extension filter.
	 *
	 * @param extension
	 *            the extension
	 */
	public ExtensionFileFilter(final String extension) {
		if (extension.startsWith(".")) {
			dotExtension = extension.toLowerCase();
		} else {
			dotExtension = "." + extension.toLowerCase();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(final File path) {
		return path.getName().toLowerCase().endsWith(dotExtension);
	}

}

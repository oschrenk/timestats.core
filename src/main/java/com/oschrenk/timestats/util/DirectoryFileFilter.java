package com.oschrenk.timestats.util;

import java.io.File;
import java.io.FileFilter;

// TODO: Auto-generated Javadoc
/**
 * Filters directories.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public class DirectoryFileFilter implements FileFilter {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(final File path) {
		return path.isDirectory();
	}

}

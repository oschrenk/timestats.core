package com.oschrenk.timestats.util;

import java.util.Comparator;

import com.oschrenk.timestats.core.Project;

/**
 * Sorts {@link Project}s in ascending alphabetical order
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class ProjectComparator implements Comparator<Project> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final Project o1, final Project o2) {
		return o1.getTitle().compareTo(o2.getTitle());
	}

}

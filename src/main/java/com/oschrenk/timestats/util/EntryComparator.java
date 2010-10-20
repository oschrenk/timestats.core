package com.oschrenk.timestats.util;

import java.util.Comparator;

import com.oschrenk.timestats.core.Entry;

/**
 * Sorts {@link Entry}s in order of ascending start dates.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 *
 */
public class EntryComparator implements Comparator<Entry> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final Entry o1, final Entry o2) {
		if (o1.getStart().isBefore(o2.getStart())) {
			return -1;
		} else if (o1.getStart().isAfter(o2.getStart())) {
			return 1;
		}
		return 0;
	}

}

package com.oschrenk.timestats.core;

import org.joda.time.DateTime;

/**
 * An entry describes an interval of time of which a {@link Project} has been
 * worked on.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Entry {

	/** The start. */
	private final DateTime start;

	/** The end. */
	private final DateTime end;

	/** The notes. */
	private final String notes;

	/** The tags. */
	private final String tags;

	/**
	 * Instantiates a new entry.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param notes
	 *            the notes
	 * @param tags
	 *            the tags
	 */
	public Entry(final DateTime start, final DateTime end, final String notes,
			final String tags) {
		super();
		this.start = start;
		this.end = end;
		this.notes = notes;
		this.tags = tags;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public DateTime getStart() {
		return start;
	}

	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public DateTime getEnd() {
		return end;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entry [start=" + start + ", end=" + end + ", notes=" + notes
				+ ", tags=" + tags + "]";
	}

}

package com.oschrenk.timestats.io;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.oschrenk.timestats.core.Entry;

/**
 * A factory for creating Entry objects.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class EntryFactory {

	/** The Constant DATETIME_PATTERN. */
	private static final String DATETIME_PATTERN = "dd.MM.yyHH:mm";

	/** The date time formatter. */
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat
			.forPattern(DATETIME_PATTERN);

	/**
	 * Builds an Entry.
	 *
	 * @param date
	 *            the date
	 * @param startTime
	 *            the start time
	 * @param endTime
	 *            the end time
	 * @param tags
	 *            the tags
	 * @param notes
	 *            the notes
	 * @return the entry
	 * @throws IllegalArgumentException
	 *             if date and time can't be parsed
	 */
	public static Entry build(final String date, final String startTime,
			final String endTime, final String tags, final String notes)
			throws IllegalArgumentException {

		DateTime start = dateTimeFormatter.parseDateTime(date + startTime);
		DateTime end = dateTimeFormatter.parseDateTime(date + endTime);
		String finalTags = (tags == null) ? "" : tags.trim();
		String finalNotes = (notes == null) ? "" : notes.trim();

		return new Entry(start, end, finalNotes, finalTags);
	}

}

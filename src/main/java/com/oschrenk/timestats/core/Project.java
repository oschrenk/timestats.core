package com.oschrenk.timestats.core;

import java.util.Set;
import java.util.TreeSet;

import com.oschrenk.timestats.util.EntryComparator;

/**
 * A project holds the {@link Entry}s and the title.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Project {

	/** The title. */
	private final String title;

	/** The entries. */
	private final Set<Entry> entries;

	/**
	 * Instantiates a new project.
	 *
	 * @param title
	 *            the title of the project
	 */
	public Project(final String title) {
		super();
		this.title = title;
		entries = new TreeSet<Entry>(new EntryComparator());
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the entries.
	 *
	 * @return the entries
	 */
	public Set<Entry> getEntries() {
		return entries;
	}

	/**
	 * Adds an {@link Entry}.
	 *
	 * @param entry
	 *            the entry
	 */
	public void add(final Entry entry) {
		entries.add(entry);
	}

}

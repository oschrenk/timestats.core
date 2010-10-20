package com.oschrenk.timestats.core;

import java.util.Set;

/**
 * Statistics is a container for all the {@link Project}s.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class Statistics {

	/** The projects. */
	private final Set<Project> projects;

	/**
	 * Instantiates a new statistics.
	 *
	 * @param projects
	 *            the projects
	 */
	public Statistics(final Set<Project> projects) {
		super();
		this.projects = projects;
	}

	/**
	 * Gets the projects.
	 *
	 * @return the projects
	 */
	public Set<Project> getProjects() {
		return projects;
	}

}

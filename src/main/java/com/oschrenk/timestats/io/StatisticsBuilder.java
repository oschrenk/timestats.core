package com.oschrenk.timestats.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.csvreader.CsvReader;
import com.oschrenk.timestats.core.Entry;
import com.oschrenk.timestats.core.Project;
import com.oschrenk.timestats.core.Statistics;
import com.oschrenk.timestats.util.DirectoryFileFilter;
import com.oschrenk.timestats.util.ExtensionFileFilter;
import com.oschrenk.timestats.util.OrCompositeFileFilter;
import com.oschrenk.timestats.util.ProjectComparator;

/**
 * Builds a new {@link Statistics} element, by reading all CVS files from the
 * given directory.
 *
 * It expectes each cvs file to have a header with a minimum of the following
 * fields set:
 *
 * <ul>
 * <li><code>Date</code></li>
 * <li><code>Start Time</code></li>
 * <li><code>End Time</code></li>
 * <li><code>Title</code></li>
 * </ul>
 *
 * The following fields are optional
 * <ul>
 * <li><code>Tags</code></li>
 * <li><code>Notes</code></li>
 * </ul>
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class StatisticsBuilder {

	/** The Constant DEFAULT_CVS_EXTENSION. */
	private static final String DEFAULT_CVS_EXTENSION = "csv";

	/** The dir. */
	private final File baseDirectory;

	/** The delimiter. */
	private final char csvFieldDelimiter;

	/** The encoding. */
	private final Charset csvFileEncoding;

	/** The filter. */
	private final FileFilter filter;

	/** The projects. */
	private final Map<String, Project> projects;

	/** The exception handler. */
	private final ExceptionHandler exceptionHandler;

	/**
	 * Instantiates a new {@link StatisticsBuilder} with the
	 * {@link DefaultExceptionHandler}
	 *
	 * @param baseDirectory
	 *            the base directory
	 * @param csvFieldDelimiter
	 *            the csv field delimiter
	 * @param csvFileEncoding
	 *            the csv file charset
	 */
	public StatisticsBuilder(final File baseDirectory,
			final char csvFieldDelimiter, final Charset csvFileEncoding) {
		this(baseDirectory, csvFieldDelimiter, csvFileEncoding,
				new DefaultExceptionHandler());
	}

	/**
	 * Instantiates a new {@link StatisticsBuilder}.
	 *
	 * @param baseDirectory
	 *            the base directory
	 * @param csvFieldDelimiter
	 *            the csv field delimiter
	 * @param csvFileEncoding
	 *            the csv file charset
	 * @param exceptionHandler
	 *            the exception handler
	 */
	public StatisticsBuilder(final File baseDirectory,
			final char csvFieldDelimiter, final Charset csvFileEncoding,
			final ExceptionHandler exceptionHandler) {
		this.baseDirectory = baseDirectory;
		this.csvFieldDelimiter = csvFieldDelimiter;
		this.csvFileEncoding = csvFileEncoding;
		this.exceptionHandler = exceptionHandler;
		filter = new OrCompositeFileFilter(new DirectoryFileFilter(),
				new ExtensionFileFilter(DEFAULT_CVS_EXTENSION));
		projects = new HashMap<String, Project>();
	}

	/**
	 * Builds the statistics.
	 *
	 * @return the statistics
	 */
	public Statistics build() {
		visitAllFiles(baseDirectory);

		Set<Project> projects = new TreeSet<Project>(new ProjectComparator());
		projects.addAll(this.projects.values());
		return new Statistics(projects);
	}

	/**
	 * Read a single cvs file. The headers must exist.
	 *
	 * <code>"Date","Start Time","End Time","Minutes","Title","Tags","Notes"</code>
	 *
	 * @param csvFile
	 *            the csv file
	 * @return the int
	 */
	private void readFile(final File csvFile) {
		CsvReader csv;
		try {
			csv = new CsvReader(new FileInputStream(csvFile),
					csvFieldDelimiter, csvFileEncoding);
			csv.readHeaders();
			while (csv.readRecord()) {
				String title = csv.get("Title");
				Entry entry = EntryFactory.build(csv.get("Date"),
						csv.get("Start Time"), csv.get("End Time"),
						csv.get("Tags"), csv.get("Notes"));
				add(title, entry);
			}

			// TODO better error handling, stack trace seems to be lost
		} catch (FileNotFoundException e) {
			exceptionHandler.handle(new StatisticsCreationException(String
					.format("Error in %s", csvFile), e));
		} catch (IOException e) {
			exceptionHandler.handle(new StatisticsCreationException(String
					.format("Error in %s", csvFile), e));
		} catch (IllegalArgumentException e) {
			exceptionHandler.handle(new StatisticsCreationException(String
					.format("Error in %s", csvFile), e));
		}

	}

	/**
	 * Adds an entry to a project. If the project doesn't exist yet, it will be
	 * created.
	 *
	 * @param title
	 *            the title of the project
	 * @param entry
	 *            the entry
	 * @throws IllegalArgumentException
	 *             if title was empty
	 */
	private void add(final String title, final Entry entry) {
		if (title == null) {
			throw new IllegalArgumentException("Empty title found.");
		}

		String cleanedTitle = title.trim();
		if (cleanedTitle.length() == 0) {
			throw new IllegalArgumentException("Empty title found.");
		}

		Project project = projects.get(cleanedTitle);
		if (project == null) {
			project = new Project(cleanedTitle);
			projects.put(cleanedTitle, project);
		}

		project.add(entry);
	}

	/**
	 * Visit all files.
	 *
	 * @param directory
	 *            the directory to visit
	 */
	private void visitAllFiles(final File directory) {
		if (directory.isDirectory()) {
			File[] children = directory.listFiles(filter);
			for (int i = 0; i < children.length; i++) {
				visitAllFiles(new File(directory, children[i].getName()));
			}
		} else {
			readFile(directory);
		}
	}

}

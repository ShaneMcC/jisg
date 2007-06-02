/*
 * Copyright (c) 2006-2007 Shane Mc Cormack
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * SVN: $Id$
 */

package uk.org.dataforce.jisg;

/**
 * JISG Logger levels.
 */
public enum LogLevel {
	/** Silent. */
	SILENT (0, "Silent"),
	/** Error. */
	ERROR (10, "Error"),
	/** Warnings. */
	WARNING (20, "Warning"),
	/** General Information. */
	INFO (30, "Info"),
	/** Debugging Information. */
	DEBUG (40, "Debug"),
	/** Advanced Debugging Information. */
	DEBUG2 (50, "Debug2");
	
	/** Number for this log level. */
	private int myLevel;
	/** Name for this log level. */
	private String myName;
	
	/**
	 * Create a new LogLevel.
	 *
	 * @param level Number for this log level.
	 * @param name Name for this log level.
	 */
	LogLevel (final int level, final String name) {
		myLevel = level;
		myName = name;
	}
	
	/**
	 * Get the number for this log level.
	 *
	 * @return number associated with this loglevel.
	 */
	public int getLevel() { return myLevel; }
	
	/**
	 * Get the name of this log level.
	 *
	 * @return name associated with this loglevel.
	 */
	public String toString() { return myName; }
	
	/**
	 * Check if this level is displayable at the given log level.
	 *
	 * @param o Given log level.
	 * @return True if the number of this loglevel is euqal or less than the given
	 *         log level.
	 */
	public boolean isLoggable(final LogLevel o) {
		return (this.getLevel() - o.getLevel()) <= 0;
	}
}
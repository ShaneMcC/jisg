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
 * This class defines the different line types we care about
 */
public enum LineType {
	TEXT (3, new String[]{"Timestamp", "Nickname", "Text"}, Integer.class, String.class, String.class),
	ACTION (3, new String[]{"Timestamp", "Nickname", "Action"}, Integer.class, String.class, String.class),
	NICK (3, new String[]{"Timestamp", "Old Nickname", "New Nickname"}, Integer.class, String.class, String.class),
	JOIN (2, new String[]{"Timestamp", "Nickname"}, Integer.class, String.class),
	LEAVE (3, new String[]{"Timestamp", "Nickname", "Reason"}, Integer.class, String.class, String.class),
	TOPIC (3, new String[]{"Timestamp", "Nickname", "Topic"}, Integer.class, String.class, String.class),
	OTHER (1, new String[]{"Timestamp"}, Integer.class),
	NONE (0, new String[]{""});
	
	/** The number of arguments this lineType has. */
	private final int count;
	
	/** The name of each argument this lineType has. */
	private final String[] names;
	
	/** The class of each argument this lineType has. */
	private final Class[] classes;
	
	
	/**
	 * Create a new LineType
	 *
	 * @param argCount How many arguments does this line type have
	 * @param argNames What is each argument
	 * @param argClasses What class is each arg?
	 */
	LineType (final int argCount, final String[] argNames, final Class... argClasses) {
		count = argCount;
		names = argNames;
		classes = argClasses;
	}
	
	/**
	 * Get the arg count for this line type
	 *
	 * @return Arg count
	 */
	 public int getCount() { return count; }
	 
	/**
	 * Get the arg names for this line type
	 *
	 * @return Arg names
	 */
	 public String[] getNames() { return names.clone(); }
	 
	/**
	 * Get the arg classes for this line type
	 *
	 * @return Arg names
	 */
	 public Class[] getClasses() { return classes.clone(); }
}

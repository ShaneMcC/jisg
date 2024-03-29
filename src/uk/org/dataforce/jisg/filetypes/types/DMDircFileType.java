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

package uk.org.dataforce.jisg.filetypes.types;

import uk.org.dataforce.jisg.filetypes.FileType;
import uk.org.dataforce.jisg.LineType;
import uk.org.dataforce.jisg.LineInfo;

/**
 * Defines the standard methods that should be implemented by filetypes.
 */
public class DMDircFileType extends FileType {
	/**
	 * Called when the filetype is constructed.
	 */
	public DMDircFileType() { }
	
	/**
	 * Get the name of the filetype.
	 *
	 * @return Name of filetype
	 */
	public String toString() { return "DMDirc"; }
	
	/**
	 * Get the line info for a given line.
	 *
	 * @param line The line to get the type of
	 * @return Info for the line
	 */
	public LineInfo getLineInfo(final String line) {
		return new LineInfo(LineType.NONE, line);
	}
}
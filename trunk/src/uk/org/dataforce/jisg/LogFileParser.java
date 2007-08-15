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

import uk.org.dataforce.jisg.filetypes.FileType;
import uk.org.dataforce.logger.Logger;
import uk.org.dataforce.logger.LogLevel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Log file parser.
 */
public class LogFileParser {
	/**
	 * Create an instance of LogFileParser.
	 */
	public LogFileParser(final FileType type, final String file) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null){
				System.out.println("\t"+type.getLineInfo(line));
			}
		} catch (java.io.FileNotFoundException e) {
			Logger.error(e.getMessage());
		} catch (IOException e) {
			Logger.error(e.getMessage());
			if (LogLevel.ERROR.isLoggable(Logger.getLevel())) {
				e.printStackTrace();
			}
		} finally {
			try { in.close(); } catch (Exception e) {}
		}
	}
}
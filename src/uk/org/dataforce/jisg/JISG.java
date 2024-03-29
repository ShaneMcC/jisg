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

import java.util.ArrayList;

import uk.org.dataforce.cliparser.CLIParser;
import uk.org.dataforce.cliparser.CLIParam;
import uk.org.dataforce.cliparser.BooleanParam;
import uk.org.dataforce.cliparser.StringParam;
import uk.org.dataforce.cliparser.IntegerParam;
import uk.org.dataforce.jisg.filetypes.FileType;
import uk.org.dataforce.jisg.filetypes.FileTypeManager;
import uk.org.dataforce.logger.Logger;
import uk.org.dataforce.logger.LogLevel;

/**
 * Main JISG class.
 */
public class JISG {
	/**
	 * Create an instance of JISG.
	 *
	 * @param args Command line arguments passed.
	 */
	public JISG(String[] args) {
		Logger.setLevel(LogLevel.INFO);
		CLIParser cli = setupCLIParser();
		if (cli.wantsHelp(args)) {
			cli.showHelp("JISG Help", "jisg [options [--]] logfile1 [logfile2 ... logfileN]");
			System.exit(0);
		}
		
		cli.parseArgs(args, true);
		
		if (cli.getParamNumber("-silent") > 0) {
			Logger.setLevel(LogLevel.SILENT);
		} else if (cli.getParamNumber("-debug") > 1) {
			Logger.info("Enabling Extra Debugging Information.");
			Logger.setLevel(LogLevel.DEBUG2);
		} else if (cli.getParamNumber("-debug") > 0) {
			Logger.info("Enabling Debugging Information.");
			Logger.setLevel(LogLevel.DEBUG);
		}
		
		FileType thisFileType = null;
		
		Logger.info("JISG Loaded.");
		if (cli.getParamNumber("-type") > 0) {
			FileTypeManager fileTypeManager = FileTypeManager.getFileTypeManager();
			String fileTypeName = cli.getParam("-type").getStringValue();
			if (!fileTypeManager.addFileType(fileTypeName+"FileType")) {
				Logger.error("Invalid file type given (I do not know about \""+fileTypeName+"\"). Terminating");
				System.exit(1);
			} else {
				thisFileType = fileTypeManager.getFileType(fileTypeName+"FileType");
				Logger.info("Set file type to: "+thisFileType);
			}
		} else {
			Logger.error("No log files type given. Terminating");
			System.exit(1);
		}
		
		ArrayList<String> redundant = cli.getRedundant();
		if (redundant.size() < 1) {
			Logger.error("No log files given. Terminating");
			System.exit(1);
		}
		
		for (int i = 0; i < redundant.size(); ++i) {
			Logger.info("Parsing File: "+redundant.get(i));
			new LogFileParser(thisFileType, redundant.get(i));
		}
		Logger.info("File parsing complete");
	}
	
	/**
	 * Setup the cli parser.
	 * This clears the current CLIParser params and creates new ones.
	 *
	 * @return the CLIParser.
	 */
	private CLIParser setupCLIParser() {
		CLIParser cli = CLIParser.getCLIParser();
		cli.clear();
		cli.add(new BooleanParam('h', "help", "Show Help"));
		cli.add(new BooleanParam('d', "debug", "Enable extra debugging. (Use twice for more)"));
		cli.add(new BooleanParam('s', "silent", "Disable all output"));
		cli.add(new StringParam('t', "type", "Type of log file"));
		cli.add(new BooleanParam((char)0, "fake", "Don't actually parse log files"));
		cli.setHelp(cli.getParam("-help"));
		return cli;
	}

	/**
	 * Run JISG
	 *
	 * @param args Command line arguments passed.
	 */
	public static void main(String[] args) {
		JISG me = new JISG(args);
	}
}
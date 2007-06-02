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

package uk.org.dataforce.jisg.filetypes;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import uk.org.dataforce.jisg.Logger;

public class FileTypeManager {
	/**
	 * List of known filetypes.
	 */
	private final Hashtable<String,FileType> knownFileTypes = new Hashtable<String,FileType>();
	
	/**
	 * Singleton instance of the filetype manager.
	 */
	private static FileTypeManager me;
	
	/**
	 * Create a new FileTypeManager.
	 */
	private FileTypeManager() { }
	
	/**
	 * Retrieves the singleton instance of the filetype manager.
	 * @return A singleton instance of FileTypeManager.
	 */
	public final static synchronized FileTypeManager getFileTypeManager() {
		if (me == null) {
			me = new FileTypeManager();
		}
		return me;
	}
		
	/**
	 * Add a new filetype.
	 *
	 * @param className Class Name of FileType object
	 * @return True if loaded, false if failed to load or if already loaded.
	 */
	public boolean addFileType(final String className) {
		final int i = className.lastIndexOf('.');
		String niceName;
		if (i > -1) { niceName = className.substring(i); }
		else  { niceName = className; }
		if (knownFileTypes.containsKey(niceName.toLowerCase())) { return false; }
		try {
			final FileType filetype = loadFileType(className);
			if (filetype == null) { return false; }
			knownFileTypes.put(niceName.toLowerCase(), filetype);
			return true;
		} catch (Exception e) {
			Logger.debug("[addFileType] Error loading "+className);
		}
		return false;
	}
	
	/**
	 * Get a filetype instance.
	 *
	 * @param name name of filetype
	 * @return FileType instance, or null
	 */
	public FileType getFileType(final String name) {
		if (!knownFileTypes.containsKey(name.toLowerCase())) { return null; }
		return knownFileTypes.get(name.toLowerCase());
	}
	
	/**
	 * Load a filetype with a given className
	 *
	 * @param className Class Name of filetype to load.
	 */
	private FileType loadFileType(final String className) {
		FileType result;
		try {
			final ClassLoader cl = ClassLoader.getSystemClassLoader();
			final String fileName = this.getClass().getPackage().getName() + ".types." + className;
			
			final Class<?> c = cl.loadClass(fileName);
			final Constructor<?> constructor = c.getConstructor(new Class[] {});
		
			final Object temp = constructor.newInstance(new Object[] {});
			
			if (temp instanceof FileType) {
				result = (FileType) temp;
			} else {
				result = null;
			}
		} catch (ClassNotFoundException cnfe) {
			Logger.debug("[LoadFileType] Class not found ('"+className+"')");
			result = null;
		} catch (NoSuchMethodException nsme) {
			Logger.debug("[LoadFileType] Constructor missing ('"+className+"')");
			result = null;
		} catch (IllegalAccessException iae) {
			Logger.debug("[LoadFileType] Unable to access constructor ('"+className+"')");
			result = null;
		} catch (InvocationTargetException ite) {
			Logger.debug("[LoadFileType] Unable to invoke target ('"+className+"')");
			result = null;
		} catch (InstantiationException ie) {
			Logger.debug("[LoadFileType] Unable to instantiate filetype ('"+className+"')");
			result = null;
		}
		
		return result;
	}
	
}

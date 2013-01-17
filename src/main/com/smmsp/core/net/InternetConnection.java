/**
 * 
 */
package com.smmsp.core.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author sean
 * 
 */
public abstract class InternetConnection {

	/**
	 * Subclasses override this method to open the connection and get the input
	 * stream.
	 * 
	 * @return
	 */
	public abstract InputStream getDataStream();

	/**
	 * Simplifies calling 'getDataStream' and routing that to a file.
	 * 
	 * @param f
	 *            The file to save the data returned by {@link #getDataStream()}
	 *            If this file already exists, it will overwrite all data inside
	 *            it.
	 * @return True if file was written properly
	 * @throws IOException 
	 */
	public boolean saveToFile(File f) throws IOException {
		return Files.copy(getDataStream(), 
				f.toPath(), 
				StandardCopyOption.REPLACE_EXISTING) > 0;
		
	}

	/**
	 * Shorthand for {@link #saveToFile(File))
	 * @param f A string path to the file
	 * @return True if the file was written properly
	 * @throws IOException
	 */
	public boolean saveToFile(String f) throws IOException {
		return saveToFile(new File(f));
	}
}

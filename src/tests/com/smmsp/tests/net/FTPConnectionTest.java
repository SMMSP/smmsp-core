/**
 * 
 */
package com.smmsp.tests.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.smmsp.core.net.FTPConnection;

/**
 * @author sean
 *
 */
public final class FTPConnectionTest {

	private static final Logger LOG = Logger.getLogger(FTPConnectionTest.class);
	
	private FTPConnectionTest(){
		// do nothing.
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(final String[] args) throws IOException {
		final FTPConnection conn = new FTPConnection("ftp://ftp.iana.org/tz/data/leapseconds");
		
		final InputStream stream = conn.getDataStream();
		final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		LOG.debug("Received: ");
		
		String line;
		final StringBuffer buf = new StringBuffer();
		while((line = reader.readLine()) != null){
			buf.append(line);
		}
		LOG.debug(buf.toString());
		
	}

}

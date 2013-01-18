/**
 * 
 */
package com.smmsp.tests.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.smmsp.core.net.FTPConnection;

/**
 * @author sean
 *
 */
public class FTPConnectionTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FTPConnection conn = new FTPConnection("ftp://ftp.iana.org/tz/data/leapseconds");
		
		InputStream stream = conn.getDataStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		System.out.println("Received: ");
		
		String line;
		while((line = reader.readLine()) != null){
			System.out.print(line);
		}
	}

}

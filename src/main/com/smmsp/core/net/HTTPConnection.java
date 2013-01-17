/*
* HTTPConnection.java
* 
*    Copyright (C) 2012 Sean P Madden
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*    If you would like to license this code under the GNU LGPL, please see
*    http://www.seanmadden.net/licensing for details.
*
*/
package com.smmsp.core.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class wraps the URL connection to provide an easy wrapper to
 * simply make HTTP GET Requests and return the data.
 *
 * @author Sean P Madden
 */
public class HTTPConnection extends InternetConnection {

	private URL _url = null;
	
	/**
	 * Creates a HTTP Connection
	 *
	 * @param u
	 */
	public HTTPConnection(URL u){
		this._url = u;
	}
	
	/**
	 * Constructor
	 * @param url
	 * @throws MalformedURLException
	 */
	public HTTPConnection(String url) throws MalformedURLException{
		_url = new URL(url);
	}
	
	/* (non-Javadoc)
	 * @see com.smmsp.core.net.InternetConnection#getDataStream()
	 */
	public InputStream getDataStream(){
		if(_url == null){
			return null;
		}
		
		try {
			HttpURLConnection conn = (HttpURLConnection)_url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setReadTimeout(10000); // 10s in millis
			
			conn.connect();
			
			return conn.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

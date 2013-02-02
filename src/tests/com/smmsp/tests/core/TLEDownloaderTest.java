/*
 * TLEDownloaderTest.java
 * 
 * Copyright (C) 2013 Sean P Madden
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 * If you would like to license this code under the GNU LGPL, please
 * see http://www.seanmadden.net/licensing for details.
 */
package com.smmsp.tests.core;

import java.io.InputStream;

import com.smmsp.core.TLE;
import com.smmsp.core.TLEProcessor;
import com.smmsp.core.net.HTTPConnection;

/**
 * @author sean
 *
 */
public class TLEDownloaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String url = "http://www.celestrak.com/NORAD/elements/goes.txt";
		HTTPConnection con = new HTTPConnection(url);
		InputStream is = con.getDataStream();
		
		TLE[] tles = TLEProcessor.fromInputStream(is);
		
		for(TLE t : tles){
			System.out.println(t);
		}
		
	}

}

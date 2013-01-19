/*
 * LeapHistoryProcessor.java
 * 
 * Copyright (C) 2012 Sean P Madden
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
package com.smmsp.core.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ProtocolException;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.smmsp.core.utils.OSAPI;
import com.smmsp.time.GregorianDate;
import com.smmsp.time.GregorianDateRange;
import com.smmsp.time.TimeConstants;

/**
 * @author sean
 *
 */
public class LeapHistoryProcessor implements Cachable{
	
	private static final Logger log = Logger.getLogger(LeapHistoryProcessor.class);
	
	private static final String LEAPS_URL = "ftp://ftp.iana.org/tz/data/leapseconds";
	
	/**
	 * Name of the file 
	 */
	private static final String LEAPS_CACHE_FILE = "leapseconds";
	
	/**
	 * Maximum length before the cache expires (1 week in seconds)
	 */
	private static final int MAX_CACHE_TIMEOUT = 604800;  

	private LinkedHashMap<GregorianDateRange, Integer> _leapsRange;
	
	/* (non-Javadoc)
	 * @see com.smmsp.core.net.Cachable#checkCache()
	 */
	public void checkCache(){
		try {
			OSAPI.ensureCacheDirExists();
		} catch (IOException e) {
			// This shouldn't happen, but if it does - log an error.
			log.error("Could not create cache directory.", e);
			return;
		}
		
		boolean needsDownload = false;
		Path fullCachedFile = Paths.get(
				OSAPI.getCacheDirectory().toString(),
				LEAPS_CACHE_FILE
				);
		
		if(Files.notExists(fullCachedFile)){
			needsDownload = true;
		}else{
			try {
				FileTime time = Files.getLastModifiedTime(fullCachedFile);
				FileTime now = FileTime.fromMillis(
						System.currentTimeMillis() + 
						MAX_CACHE_TIMEOUT * 1000);
				
				if(now.compareTo(time) < 0 ){
					//cache is invalidated.
					needsDownload = true;
				}
			} catch (IOException e) {
				log.error("Unable to get file modification time on leaps cache", e);
				return;
			}
		}
		
		// if we don't need to update the cache, bail out.
		if(!needsDownload){
			return;
		}
		
		
		try {
			FTPConnection conn = new FTPConnection(LEAPS_URL);
			
			ReadableByteChannel ftpChan = Channels.newChannel(conn.getDataStream());
			
			// create the file if it doesn't exist and
			// hose it if it does
			FileChannel cachedFile = FileChannel.open(
					fullCachedFile, 
					StandardOpenOption.CREATE, 
					StandardOpenOption.WRITE, 
					StandardOpenOption.TRUNCATE_EXISTING);
			
			// pipe it to the file.
			cachedFile.transferFrom(ftpChan, 0, Integer.MAX_VALUE);
		} catch (ProtocolException e) {
			e.printStackTrace();
			log.error("Error with FTP download from IANA.", e);
		} catch (IOException e){
			e.printStackTrace();
			log.error("Error file file IO.", e);
		}
		
	}
	
	private void populateHistory(){
		checkCache();
		Path fullCachedFile = Paths.get(
				OSAPI.getCacheDirectory().toString(),
				LEAPS_CACHE_FILE
				);
		try {
			FileChannel cachedFile = FileChannel.open(
					fullCachedFile,
					StandardOpenOption.READ
					);
			
			BufferedReader reader = new BufferedReader(
					Channels.newReader(cachedFile, "UTF-8"));
			
			String line = null;
			GregorianDate previousDate = null;
			int currentLeaps = 0;
			
			while((line = reader.readLine()) != null){
				line = line.trim();
				if("".equals(line)){
					continue; // blank lines ignored
				}else if(line.startsWith("#")){
					continue; // comments ignored
				}else if(!line.startsWith("Leap")){
					continue; // only looking for leaps
				}
				
				String[] fields = line.split("\t");
				if(!"Leap".equals(fields[0])){
					// fields[0] should be leap, if not, processing error
					continue;
				}
				int year = Integer.parseInt(fields[1]);
				String month = fields[2];
				int day = Integer.parseInt(fields[3]);
				
				if("+".equals(fields[5])){
					++currentLeaps;
				}else{
					--currentLeaps;
				}
				
				GregorianDate date = new GregorianDate(
						year, 
						TimeConstants.MonthStringToInt(month), 
						day);
				GregorianDateRange range = 
						new GregorianDateRange(previousDate, date);
				_leapsRange.put(range, currentLeaps);
				
				previousDate = date;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Unable to read leaps cache file. ", e);
			return;
		}
		
		
	}
	
	public int getLeapsForDate(GregorianDate date){
		if(_leapsRange == null ){
			_leapsRange = new LinkedHashMap<>();
			populateHistory();
		}
		
		Map.Entry<GregorianDateRange, Integer> finalRange = null;
		for(Map.Entry<GregorianDateRange, Integer> ents : _leapsRange.entrySet()){
			GregorianDateRange range = ents.getKey();
			System.out.println(ents);
//			if(range.isBefore(date)){
//				
//			}
			finalRange = ents;
		}
		
		if(finalRange.getKey().isWithinRange(date)){
			return finalRange.getValue();
		}
		
		return 0;
	}
}

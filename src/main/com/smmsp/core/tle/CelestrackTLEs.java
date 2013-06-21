/*
 * CelestrackTLEs.java
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
package com.smmsp.core.tle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.smmsp.core.net.HTTPCachedFile;

/**
 * Three/Two Line Element sets provided by Celestrak.
 * 
 * @author sean
 */
public final class CelestrackTLEs {

	private static final Logger LOG = Logger.getLogger(CelestrackTLEs.class);

	private CelestrackTLEs() {
		// does nothing.
	}

	public static final Map<String, List<TLESetLocation>> 
			AVAILABLE_TLE_LOCATIONS = new LinkedHashMap<>();
	static{
		reloadFromTLEFiles();
	}
	
	/**
	 * Updates the local caches for these files.
	 */
	public static void updateCache() {
		for (final List<TLESetLocation> locations : AVAILABLE_TLE_LOCATIONS.values()) {
			for(final TLESetLocation tle: locations){
				final HTTPCachedFile file = new HTTPCachedFile(tle.getName(),
						tle.getHttpUrl());
				if (file.cacheNeedsUpdate()) {
					file.updateCache();
					LOG.info("Updated cache for " + tle.getName());
				}
			}
		}
		reloadFromTLEFiles();
	}
	
	protected static void reloadFromTLEFiles(){
		AVAILABLE_TLE_LOCATIONS.clear();
		try {
			final URL url = CelestrackTLEs.class.getResource("/tles/");
			final URI uri = url.toURI();
			final Path path = Paths.get(uri);
			final DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*.xml");
			final Iterator<Path> rator = ds.iterator();
			while(rator.hasNext()){
				final Path p = rator.next();
				final TLESetList list = getListFromXMLLocation(p);
				AVAILABLE_TLE_LOCATIONS.put(list.getName(), list.getLocationList());
			}
		} catch (IOException e) {
			LOG.error(e);
		} catch (URISyntaxException e) {
			LOG.error(e);
		}
	}

	/**
	 * Retrieves and returns a list of TLESetLocations from the
	 * specified XML file.
	 * @param xmlLocation
	 * @return
	 */
	protected static TLESetList getListFromXMLLocation(
			final Path xmlLocation) {
		try {
			final InputStream is = Files.newInputStream(xmlLocation,
					StandardOpenOption.READ
					);
			final JAXBContext ctx = JAXBContext.newInstance(TLESetList.class);
			final Unmarshaller unmarsh = ctx.createUnmarshaller();
			return (TLESetList) unmarsh.unmarshal(is);
		} catch (JAXBException e) {
			LOG.error("JAXB XML error", e);
		} catch (IOException e) {
			LOG.error("IO Exception", e);
		}
		return null;
	}

}

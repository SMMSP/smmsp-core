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

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.smmsp.core.net.HTTPCachedFile;
import com.smmsp.tests.core.CelestrakCacheUpdater;

/**
 * Three/Two Line Element sets provided by Celestrak.
 * 
 * @author sean
 */
public final class CelestrackTLEs {

	private static final Logger log = Logger.getLogger(CelestrackTLEs.class);

	private CelestrackTLEs() {
		// does nothing.
	}

	/**
	 * Special Interest satellites from Celestrak
	 */
	public static final List<TLESetLocation> SPECIAL_INTEREST;
	static {
		SPECIAL_INTEREST = getListFromXMLLocation("/tles/SpecialInterest.xml");
	}
	
	/**
	 * Weather & Earth Resources Satellites from celestrak
	 */
	public static final List<TLESetLocation> WEATHER;
	static {
		WEATHER = getListFromXMLLocation("/tles/Weather.xml");
	}
	
	/**
	 * Communications satellites from Celestrak
	 */
	public static final List<TLESetLocation> COMMUNICATIONS;
	static {
		COMMUNICATIONS = getListFromXMLLocation("/tles/Communications.xml");
	}

	/**
	 * Updates the local caches for these files.
	 */
	public static void updateCache() {
		List<TLESetLocation> allLocations = new LinkedList<>();
		allLocations.addAll(SPECIAL_INTEREST);
		allLocations.addAll(WEATHER);
		allLocations.addAll(COMMUNICATIONS);

		for (TLESetLocation tle : allLocations) {
			final HTTPCachedFile file = new HTTPCachedFile(tle.getName(),
					tle.getHttpUrl());
			if (file.cacheNeedsUpdate()) {
				file.updateCache();
				log.info("Updated cache for " + tle.getName());
			}
		}
	}

	/**
	 * Retrieves and returns a list of TLESetLocations from the
	 * specified XML file.
	 * @param xmlLocation
	 * @return
	 */
	protected static List<TLESetLocation> getListFromXMLLocation(
			final String xmlLocation) {
		try {
			InputStream is = CelestrakCacheUpdater.class
					.getResourceAsStream(xmlLocation);

			JAXBContext ctx = JAXBContext.newInstance(TLESetList.class);
			Unmarshaller unmarsh = ctx.createUnmarshaller();
			TLESetList list = (TLESetList) unmarsh.unmarshal(is);
			return list.getLocationList();

		} catch (JAXBException e) {
			log.error("JAXB XML error", e);
		}
		return null;
	}

}

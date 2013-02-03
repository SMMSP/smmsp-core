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

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.smmsp.core.net.HTTPCachedFile;

/**
 * @author sean
 * 
 */
public final class CelestrackTLEs {

	private static final Logger log = Logger.getLogger(CelestrackTLEs.class);

	private CelestrackTLEs() {
		// does nothing.
	}

	@SuppressWarnings("serial")
	public static final List<TLESetLocation> SPECIAL_INTEREST = new Vector<TLESetLocation>() {
		{
			add(new TLESetLocation() {
				{
					name = "Last30DaysLaunches";
					httpUrl = "http://www.celestrak.com/NORAD/elements/tle-new.txt";
					description = "New launches in the last 30 days.";
				}
			});
			add(new TLESetLocation() {
				{
					name = "SpaceStations";
					httpUrl = "http://www.celestrak.com/NORAD/elements/stations.txt";
					description = "Space Stations in Orbit";
				}
			});
			add(new TLESetLocation() {
				{
					name = "TopBrightest";
					httpUrl = "http://www.celestrak.com/NORAD/elements/visual.txt";
					description = "Top 100 (or so) brightest in orbit";
				}
			});
			add(new TLESetLocation() {
				{
					name = "FENGYUN1C-Debris";
					httpUrl = "http://www.celestrak.com/NORAD/elements/1999-025.txt";
					description = "Weather satellite destroyed by Chinese missile test";
				}
			});
			add(new TLESetLocation() {
				{
					name = "Iridium33-Debris";
					httpUrl = "http://www.celestrak.com/NORAD/elements/iridium-33-debris.txt";
					description = "Collided with Cosmos 2251 in 2009";
				}
			});
			add(new TLESetLocation() {
				{
					name = "Cosmos2251-Debris";
					httpUrl = "http://www.celestrak.com/NORAD/elements/cosmos-2251-debris.txt";
					description = "Collided with Iridium 33 in 2009";
				}
			});
			add(new TLESetLocation() {
				{
					name = "BreezeMR-C";
					httpUrl = "http://www.celestrak.com/NORAD/elements/2012-044.txt";
					description = "Debris of Breeze M R/C Rocket Body";
				}
			});
		}
	};

	@SuppressWarnings("serial")
	public static final List<TLESetLocation> WEATHER = new Vector<TLESetLocation>() {
		{
			add(new TLESetLocation() {
				{
					name = "Weather";
					httpUrl = "http://www.celestrak.com/NORAD/elements/weather.txt";
					description = "Weather Satellites";
				}
			});
			add(new TLESetLocation() {
				{
					name = "NOAA";
					httpUrl = "http://www.celestrak.com/NORAD/elements/noaa.txt";
					description = "All NOAA Satellites";
				}
			});
			add(new TLESetLocation() {
				{
					name = "GOES";
					httpUrl = "http://www.celestrak.com/NORAD/elements/goes.txt";
					description = "All GOES Satellites";
				}
			});
			add(new TLESetLocation() {
				{
					name = "EarthResources";
					httpUrl = "http://www.celestrak.com/NORAD/elements/resource.txt";
					description = "All Earth Resource Satellites";
				}
			});
			add(new TLESetLocation() {
				{
					name = "SARSAT";
					httpUrl = "http://www.celestrak.com/NORAD/elements/sarsat.txt";
					description = "Search and Rescue Satellites";
				}
			});
			add(new TLESetLocation() {
				{
					name = "DisasterMonitoring";
					httpUrl = "http://www.celestrak.com/NORAD/elements/dmc.txt";
					description = "Disaster Monitoring Satellites";
				}
			});
			add(new TLESetLocation() {
				{
					name = "TDRSS";
					httpUrl = "http://www.celestrak.com/NORAD/elements/tdrss.txt";
					description = "NASA's Tracking and Data Relay Satellite System (TDRSS)";
				}
			});
		}
	};

	/**
	 * Updates the local caches for these files.
	 */
	public static void updateCache() {
		List<TLESetLocation> allLocations = new LinkedList<>();
		allLocations.addAll(SPECIAL_INTEREST);
		allLocations.addAll(WEATHER);

		for (TLESetLocation tle : allLocations) {
			final HTTPCachedFile file = new HTTPCachedFile(tle.name,
					tle.httpUrl);
			if (file.cacheNeedsUpdate()) {
				file.updateCache();
				log.info("Updated cache for " + tle.name);
			}
		}
	}

}

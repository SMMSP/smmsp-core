/*
 * CelestrakCacheUpdater.java
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


import java.util.List;
import java.util.Map;

import com.smmsp.core.tle.CelestrackTLEs;
import com.smmsp.core.tle.TLESetLocation;

/**
 * @author sean
 * 
 */
public class CelestrakCacheUpdater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CelestrackTLEs.updateCache();
		for(Map.Entry<String, List<TLESetLocation>> ent : 
			CelestrackTLEs.AVAILABLE_TLE_LOCATIONS.entrySet()){
			System.out.println(ent);
		}
	}

}

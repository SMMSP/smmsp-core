/*
 * KeplerianOrbit.java
 * Copyright (C) 2012 Sean P Madden
 * This program is free software: you can redistribute it and/or
 * modify
 * it under the terms of the GNU General Public License as published
 * by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 * If you would like to license this code under the GNU LGPL, please
 * see
 * http://www.seanmadden.net/licensing for details.
 */
package com.smmsp.astro;

import com.smmsp.time.TimeInstant;
import com.smmsp.time.UnixTime;

/**
 * TODO comment this.
 * 
 * @author Sean P Madden
 */
public class KeplerianOrbit {

	TimeInstant epoch = new UnixTime();

	double inclination = 0;

	double raan = 0;

	double eccentricity = 0;

	double argumentOfPerigee = 0;

	double meanAnomaly = 0;

	double meanMotion = 0;

	public KeplerianOrbit(TimeInstant t){
		
	}
	
	public int getPropogationTime(TimeInstant t){
		return 0;
	}

}

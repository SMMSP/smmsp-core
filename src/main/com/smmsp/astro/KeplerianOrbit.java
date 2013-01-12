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


import com.smmsp.time.TimeException;
import com.smmsp.time.TimeInstant;
import com.smmsp.time.UnixTime;

/**
 * TODO comment this.
 * 
 * @author Sean P Madden
 */
public class KeplerianOrbit {

	private TimeInstant	_epoch				= new UnixTime();

	private double		_inclination		= 0;

	private double		_raan				= 0;

	private double		_eccentricity		= 0;

	private double		_argumentOfPerigee	= 0;

	private double		_meanAnomaly		= 0;

	private double		_meanMotion			= 0;
	
	public KeplerianOrbit(TimeInstant epoch){
		_epoch = epoch;
	}

	public synchronized double getPropogationTime(TimeInstant atTime) throws TimeException {
		double propTimeInSolarDays = 0;
		propTimeInSolarDays = atTime.toUnixTime().toTimestamp()
				- _epoch.toUnixTime().toTimestamp();

		// a solar day is exactly 24 hours long
		propTimeInSolarDays /= 86400;

		return propTimeInSolarDays;
	}

}

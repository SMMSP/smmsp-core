/*
 * SolarBody.java
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
package com.smmsp.astro;

/**
 * Interface representing a solar body
 * @author sean
 */
public interface SolarBody
{
	public double getRadiusInKM();

	/**
	 * @return the massInKG
	 */
	public double getMassInKG();

	/**
	 * @return the siderealRotationInDays
	 */
	public double getSiderealRotationInDays();

	/**
	 * @return the equatorialInclination
	 */
	public double getEquatorialInclination();

	/**
	 * @return the semimajorOrbitalAxis
	 */
	public double getSemimajorOrbitalAxis();

	/**
	 * @return the eccentricity
	 */
	public double getEccentricity();

	/**
	 * @return the orbitalInclination
	 */
	public double getOrbitalInclination();

	/**
	 * @return the siderealOrbitalPeriodInDays
	 */
	public double getSiderealOrbitalPeriodInDays();

	/**
	 * @return the gravitationalParameter
	 */
	public double getGravitationalParameter();

	/**
	 * @return the aphelion
	 */
	public double getAphelion();

	/**
	 * @return the perihelion
	 */
	public double getPerihelion();

	/**
	 * @return the orbitalPeriodInDays
	 */
	public double getOrbitalPeriodInDays();

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @return the semimajorBodyAxis
	 */
	public double getSemimajorBodyAxis();

	/**
	 * @return the semiminorBodyAxis
	 */
	public double getSemiminorBodyAxis();

}

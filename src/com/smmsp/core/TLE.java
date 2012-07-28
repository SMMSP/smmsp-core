/*
 * TLE.java
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
package com.smmsp.core;

import java.util.Calendar;

/**
 * This interface describes a 'TLE' or a Two/Three Line element set
 * 
 * @author Sean P Madden
 */
public interface TLE {

	/*
	 * The following can be pulled from the first line of the TLE Info
	 * line (first actual line).
	 */
	/**
	 * Returns the common name of the satellite (as described in the
	 * TLE)
	 * 
	 * @return String representing the name (Empty if not found)
	 */
	public String getSatelliteName();

	/*
	 * The following can be pulled from the first line of the TLE
	 * Data. (First data line).
	 */
	/**
	 * Returns the international satellite number
	 * 
	 * @return Integer representing the number
	 */
	public int getSatelliteNumber();

	/**
	 * Returns the classification of this TLE. For 99% of all cases,
	 * This will return UNCLASSIFIED
	 * 
	 * @return Classification of the satellite.
	 */
	public Classification getClassification();

	/**
	 * Returns the international designator of the Satellite. This is
	 * in the format of YYNNNXX where
	 * YY -> Last two digits of the launch year (57-99 19xx,00-56
	 * 20xx)
	 * NNN -> Launch number of that year
	 * XX -> Piece of the launch (Alpha numeric)
	 * 
	 * @return String representing the international designator.
	 */
	public String getInternationalDesignator();

	/**
	 * Returns the epoch of this TLE.
	 * 
	 * @return Epoch starting point of the TLE.
	 */
	public Calendar getEpoch();

	/**
	 * Returns the first time derivative of the mean motion (divided
	 * by two). Multiply this number by two to get the true first
	 * time derivative of the mean motion. This desribes how the
	 * mean motion changes day over day so propogators can accurately
	 * describe how the satellite moves for longer periods of time.
	 * 
	 * @return First time derivative of the mean motion in
	 *         orbits/day^2
	 */
	public double getDotMeanMotion();

	/**
	 * Returns the second time derivative of the mean motion (divided
	 * by six). This defines the rate of change of the Dot mean motion
	 * day to day. This will normally be zero unless the satellite is
	 * being maneuvered or is undergoing orbital delay.
	 * 
	 * @return second time deriv of the mean motion in orbits/day^3
	 */
	public double getDotDotMeanMotion();

	/**
	 * The BSTAR Drag Term is used in the SGP4 type propogator for
	 * use in estimating atmospheric drag on the satellites motion.
	 * 
	 * @return The BSTAR Drag term (units are 1/EarthRadiii)
	 */
	public double getBSTARDragTerm();

	/**
	 * Returns the Element Set Number. These are used to distinguish
	 * individual TLEs over time. When a new TLE is released, this
	 * number is incremented.
	 * 
	 * @return Element Set Number for this TLE.
	 */
	public int getElementSetNumber();

	/*
	 * The following can be pulled from the second line of the TLE
	 * Data. (The last line)
	 */
	/**
	 * Returns the inclination (in degrees) above the earth's
	 * equatorial plane. Between 0 and 90 is known as a prograde
	 * orbit, 90 to 180 is a retrograde.
	 * 
	 * @return The orbital inclination in degrees.
	 */
	public double getInclinationDeg();

	/**
	* Returns the Right Ascension of the Ascending Node - the 
	* geocentric right ascension as the satellite as it intersects
	* the equatorial plane as it travels northward (in degrees).
	* 
	* @return The RAAN of the satellite in degrees.
	*/
	public double getRAANDeg();

	/**
	* Returns the Eccentricity of the satellites orbit. This is a 
	* unitless value representing the ratio of the orbit's focus 
	* distance to the orbit's semi-major axis.
	* 
	* @return The eccentricity of this orbit.
	*/
	public double getEccentricity();

	/**
	* Returns the Argument of Perigee - the angle measured from the 
	* ascending node to the point of perigee.  This defines in which
	* direction the orbit is eccentric.
	* 
	* @return The argument of perigee in degrees.
	*/
	public double getArgPerigeeDeg();

	/**
	* Returns the mean anomaly in degrees.  This is the position of
	* the satellite in the orbit when the epoch was snapshotted.
	* 
	* @return The satellites position in degrees from the epoch.
	*/
	public double getMeanAnomalyDeg();

	/**
	* Returns the mean motion of the satellite.  It is defined as 
	* the number of orbits (revolutions) the satellite completes
	* about the earth in one solar day (24 hours)
	* 
	* @return The mean motion in rotations per day
	*/
	public double getMeanMotionRPD();

	/**
	* Returns the number of orbits the satellite has completed from
	* the point of launch to the epoch.
	* 
	* @return Number of orbits completed at the epoch.
	*/
	public int getRevNumberAtEpoch();

}

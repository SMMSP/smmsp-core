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
package com.smmsp.core.tle;

import com.smmsp.core.Classification;
import com.smmsp.time.TimeInstant;

/**
 * This class describes a 'TLE' or a Two/Three Line element set
 * 
 * @author Sean P Madden
 */
public class TLE {
	
	/**
	 * The common name of the satellite (as described in the TLE)
	 */
	public String satelliteName;

	/**
	 * Returns the international satellite number
	 */
	public int satelliteNumber;

	/**
	 * Returns the classification of this TLE. For 99% of all cases, This will
	 * return UNCLASSIFIED
	 */
	public Classification classification;

	/**
	 * Returns the international designator of the Satellite. This is in the
	 * format of YYNNNXX where YY -> Last two digits of the launch year (57-99
	 * 19xx,00-56 20xx) NNN -> Launch number of that year XX -> Piece of the
	 * launch (Alpha numeric)
	 */
	public String internationalDesignator;

	/**
	 * Returns the epoch of this TLE.
	 */
	public TimeInstant epoch;

	/**
	 * Returns the first time derivative of the mean motion (divided by two).
	 * Multiply this number by two to get the true first time derivative of the
	 * mean motion. This desribes how the mean motion changes day over day so
	 * propogators can accurately describe how the satellite moves for longer
	 * periods of time.
	 */
	public double dotMeanMotion;

	/**
	 * Returns the second time derivative of the mean motion (divided by six).
	 * This defines the rate of change of the Dot mean motion day to day. This
	 * will normally be zero unless the satellite is being maneuvered or is
	 * undergoing orbital delay.
	 */
	public double dotDotMeanMotion;

	/**
	 * The BSTAR Drag Term is used in the SGP4 type propogator for use in
	 * estimating atmospheric drag on the satellites motion.
	 */
	public double bstarDragTerm;

	/**
	 * Returns the Element Set Number. These are used to distinguish individual
	 * TLEs over time. When a new TLE is released, this number is incremented.
	 */
	public int elementSetNumber;

	/*
	 * The following can be pulled from the second line of the TLE Data. (The
	 * last line)
	 */
	/**
	 * Returns the inclination (in degrees) above the earth's equatorial plane.
	 * Between 0 and 90 is known as a prograde orbit, 90 to 180 is a retrograde.
	 */
	public double inclinationDeg;

	/**
	 * Returns the Right Ascension of the Ascending Node - the geocentric right
	 * ascension as the satellite as it intersects the equatorial plane as it
	 * travels northward (in degrees).
	 */
	public double raanDeg;

	/**
	 * Returns the Eccentricity of the satellites orbit. This is a unitless
	 * value representing the ratio of the orbit's focus distance to the orbit's
	 * semi-major axis.
	 */
	public double eccentricity;

	/**
	 * Returns the Argument of Perigee - the angle measured from the ascending
	 * node to the point of perigee. This defines in which direction the orbit
	 * is eccentric.
	 */
	public double argPerigeeDeg;

	/**
	 * Returns the mean anomaly in degrees. This is the position of the
	 * satellite in the orbit when the epoch was snapshotted.
	 */
	public double meanAnomalyDeg;

	/**
	 * Returns the mean motion of the satellite. It is defined as the number of
	 * orbits (revolutions) the satellite completes about the earth in one solar
	 * day (24 hours)
	 */
	public double meanMotionRPD;

	/**
	 * Returns the number of orbits the satellite has completed from the point
	 * of launch to the epoch.
	 */
	public int revNumberAtEpoch;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(argPerigeeDeg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(bstarDragTerm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((classification == null) ? 0 : classification.hashCode());
		temp = Double.doubleToLongBits(dotDotMeanMotion);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(dotMeanMotion);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(eccentricity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + elementSetNumber;
		result = prime * result + ((epoch == null) ? 0 : epoch.hashCode());
		temp = Double.doubleToLongBits(inclinationDeg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((internationalDesignator == null) ? 0
						: internationalDesignator.hashCode());
		temp = Double.doubleToLongBits(meanAnomalyDeg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(meanMotionRPD);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(raanDeg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + revNumberAtEpoch;
		result = prime * result
				+ ((satelliteName == null) ? 0 : satelliteName.hashCode());
		result = prime * result + satelliteNumber;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TLE)) {
			return false;
		}
		TLE other = (TLE) obj;
		if (Double.doubleToLongBits(argPerigeeDeg) != Double
				.doubleToLongBits(other.argPerigeeDeg)) {
			return false;
		}
		if (Double.doubleToLongBits(bstarDragTerm) != Double
				.doubleToLongBits(other.bstarDragTerm)) {
			return false;
		}
		if (classification != other.classification) {
			return false;
		}
		if (Double.doubleToLongBits(dotDotMeanMotion) != Double
				.doubleToLongBits(other.dotDotMeanMotion)) {
			return false;
		}
		if (Double.doubleToLongBits(dotMeanMotion) != Double
				.doubleToLongBits(other.dotMeanMotion)) {
			return false;
		}
		if (Double.doubleToLongBits(eccentricity) != Double
				.doubleToLongBits(other.eccentricity)) {
			return false;
		}
		if (elementSetNumber != other.elementSetNumber) {
			return false;
		}
		if (epoch == null) {
			if (other.epoch != null) {
				return false;
			}
		} else if (!epoch.equals(other.epoch)) {
			return false;
		}
		if (Double.doubleToLongBits(inclinationDeg) != Double
				.doubleToLongBits(other.inclinationDeg)) {
			return false;
		}
		if (internationalDesignator == null) {
			if (other.internationalDesignator != null) {
				return false;
			}
		} else if (!internationalDesignator
				.equals(other.internationalDesignator)) {
			return false;
		}
		if (Double.doubleToLongBits(meanAnomalyDeg) != Double
				.doubleToLongBits(other.meanAnomalyDeg)) {
			return false;
		}
		if (Double.doubleToLongBits(meanMotionRPD) != Double
				.doubleToLongBits(other.meanMotionRPD)) {
			return false;
		}
		if (Double.doubleToLongBits(raanDeg) != Double
				.doubleToLongBits(other.raanDeg)) {
			return false;
		}
		if (revNumberAtEpoch != other.revNumberAtEpoch) {
			return false;
		}
		if (satelliteName == null) {
			if (other.satelliteName != null) {
				return false;
			}
		} else if (!satelliteName.equals(other.satelliteName)) {
			return false;
		}
		if (satelliteNumber != other.satelliteNumber) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TLE [satelliteName=");
		builder.append(satelliteName);
		builder.append(", satelliteNumber=");
		builder.append(satelliteNumber);
		builder.append(", classification=");
		builder.append(classification);
		builder.append(", internationalDesignator=");
		builder.append(internationalDesignator);
		builder.append(", epoch=");
		builder.append(epoch);
		builder.append(", dotMeanMotion=");
		builder.append(dotMeanMotion);
		builder.append(", dotDotMeanMotion=");
		builder.append(dotDotMeanMotion);
		builder.append(", bstarDragTerm=");
		builder.append(bstarDragTerm);
		builder.append(", elementSetNumber=");
		builder.append(elementSetNumber);
		builder.append(", inclinationDeg=");
		builder.append(inclinationDeg);
		builder.append(", raanDeg=");
		builder.append(raanDeg);
		builder.append(", eccentricity=");
		builder.append(eccentricity);
		builder.append(", argPerigeeDeg=");
		builder.append(argPerigeeDeg);
		builder.append(", meanAnomalyDeg=");
		builder.append(meanAnomalyDeg);
		builder.append(", meanMotionRPD=");
		builder.append(meanMotionRPD);
		builder.append(", revNumberAtEpoch=");
		builder.append(revNumberAtEpoch);
		builder.append("]");
		return builder.toString();
	}

	
}

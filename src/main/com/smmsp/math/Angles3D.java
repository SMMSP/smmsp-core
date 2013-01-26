/*
 * Angles3D.java
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
package com.smmsp.math;

/**
 * This class defines a series of angles from a vector in three
 * dimensional space.  Radian angles are ensured to be in the range
 * [0, 2*PI) while degree angles are ensured to be in the range
 * [0, 360.)
 * @author sean
 *
 */
public class Angles3D {

	/**
	 * Enumeration to specify which types of angles are stored within.
	 * @author sean
	 *
	 */
	public static enum AngleType{
		RADIANS, DEGREES
	}
	
	/**
	 * Precomputed math for quick conversions.
	 */
	public static final double TO_RADIANS = Math.PI / 180.;
	
	/**
	 * Precomputed math for quick conversions.
	 */
	public static final double TO_DEGREES = 180. / Math.PI;
	
	/**
	 * Precomputed math for quick conversions.
	 */
	public static final double TWO_PI = 2.0 * Math.PI;
	
	/**
	 * Magnitude of the angle in the I direction
	 */
	private double thetaI;
	
	/**
	 * Magnitude of the angle in the J direction 
	 */
	private double thetaJ;
	
	/**
	 * Magnitude of the angle in the K direction 
	 */
	private double thetaK;
	
	/**
	 * Which type of angle is this?
	 */
	private AngleType type = AngleType.DEGREES;
	
	/**
	 * Constructor, initializes all the angles to zero - assumes
	 * angles are in degrees.
	 */
	public Angles3D(){
		thetaI = thetaJ = thetaK = 0;
		type = AngleType.DEGREES;
	}
	
	/**
	 * Constructor - assumes all angles are in Degrees.
	 * @param _thetaI
	 * @param _thetaJ
	 * @param _thetaK
	 */
	public Angles3D(
			final double _thetaI, 
			final double _thetaJ, 
			final double _thetaK) {
		this.thetaI = _thetaI;
		this.thetaJ = _thetaJ;
		this.thetaK = _thetaK;
		this.type = AngleType.DEGREES;
	}
	
	/**
	 * Constructor.  Accepts all angles.
	 * @param _thetaI
	 * @param _thetaJ
	 * @param _thetaK
	 * @param _type
	 */
	public Angles3D(
			final double _thetaI, 
			final double _thetaJ, 
			final double _thetaK, 
			final AngleType _type){
		this(_thetaI, _thetaJ, _thetaK);
		this.type = _type;
	}
	
	/**
	 * Copy constructor!
	 * @param other
	 */
	public Angles3D(final Angles3D other){
		this.thetaI = other.thetaI;
		this.thetaJ = other.thetaJ;
		this.thetaK = other.thetaK;
		this.type = other.type;
	}

	/**
	 * Converts the angles stored within to radians and returns the
	 * result in a new Angles3D
	 * 
	 * @return
	 */
	public Angles3D toRadians(){
		if(type == AngleType.RADIANS){
			// we're already in degrees.
			return new Angles3D(this);
		}
		
		final Angles3D ret = new Angles3D();
		
		ret.thetaI = (thetaI * TO_RADIANS) % TWO_PI;
		ret.thetaJ = (thetaJ * TO_RADIANS) % TWO_PI;
		ret.thetaK = (thetaK * TO_RADIANS) % TWO_PI;
		ret.type = AngleType.RADIANS;
		
		return ret;
	}
	
	/**
	 * Converts the angles stored within to degrees and returns the
	 * result in a new Angles3D
	 * @return
	 */
	public Angles3D toDegrees(){
		if(type == AngleType.DEGREES){
			// we're already in degrees
			return new Angles3D(this);
		}
		
		final Angles3D ret = new Angles3D();
		
		ret.thetaI = (thetaI * TO_DEGREES) % 360.;
		ret.thetaJ = (thetaJ * TO_DEGREES) % 360.;
		ret.thetaK = (thetaK * TO_DEGREES) % 360.;
		ret.type = AngleType.DEGREES;
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Angles3D [_thetaI=" + thetaI + ", _thetaJ=" + thetaJ
				+ ", _thetaK=" + thetaK + ", _type=" + type + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(thetaI);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(thetaJ);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(thetaK);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		final Angles3D other = (Angles3D) obj;
		if (Double.doubleToLongBits(thetaI) != Double
				.doubleToLongBits(other.thetaI)){
			return false;
		}
		if (Double.doubleToLongBits(thetaJ) != Double
				.doubleToLongBits(other.thetaJ)){
			return false;
		}
		if (Double.doubleToLongBits(thetaK) != Double
				.doubleToLongBits(other.thetaK)){
			return false;
		}
		if (type != other.type){
			return false;
		}
		return true;
	}

	/**
	 * @return the _thetaI
	 */
	public double getThetaI() {
		return thetaI;
	}

	/**
	 * @param _thetaI the _thetaI to set
	 */
	public void setThetaI(final double _thetaI) {
		this.thetaI = _thetaI;
	}

	/**
	 * @return the _thetaJ
	 */
	public double getThetaJ() {
		return thetaJ;
	}

	/**
	 * @param _thetaJ the _thetaJ to set
	 */
	public void setThetaJ(final double _thetaJ) {
		this.thetaJ = _thetaJ;
	}

	/**
	 * @return the _thetaK
	 */
	public double getThetaK() {
		return thetaK;
	}

	/**
	 * @param _thetaK the _thetaK to set
	 */
	public void setThetaK(final double _thetaK) {
		this.thetaK = _thetaK;
	}

	/**
	 * @return the _type
	 */
	public AngleType getType() {
		return type;
	}

	/**
	 * @param _type the _type to set
	 */
	public void setType(final AngleType _type) {
		this.type = _type;
	}
	
}

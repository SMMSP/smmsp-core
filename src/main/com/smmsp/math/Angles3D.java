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
	private double _thetaI;
	
	/**
	 * Magnitude of the angle in the J direction 
	 */
	private double _thetaJ;
	
	/**
	 * Magnitude of the angle in the K direction 
	 */
	private double _thetaK;
	
	/**
	 * Which type of angle is this?
	 */
	private AngleType _type = AngleType.DEGREES;
	
	/**
	 * Constructor, initializes all the angles to zero - assumes
	 * angles are in degrees.
	 */
	public Angles3D(){
		_thetaI = _thetaJ = _thetaK = 0;
		_type = AngleType.DEGREES;
	}
	
	/**
	 * Constructor - assumes all angles are in Degrees.
	 * @param _thetaI
	 * @param _thetaJ
	 * @param _thetaK
	 */
	public Angles3D(double _thetaI, double _thetaJ, double _thetaK) {
		this._thetaI = _thetaI;
		this._thetaJ = _thetaJ;
		this._thetaK = _thetaK;
		this._type = AngleType.DEGREES;
	}
	
	/**
	 * Constructor.  Accepts all angles.
	 * @param _thetaI
	 * @param _thetaJ
	 * @param _thetaK
	 * @param _type
	 */
	public Angles3D(
			double _thetaI, 
			double _thetaJ, 
			double _thetaK, 
			AngleType _type){
		this(_thetaI, _thetaJ, _thetaK);
		this._type = _type;
	}
	
	/**
	 * Copy constructor!
	 * @param other
	 */
	public Angles3D(final Angles3D other){
		this._thetaI = other._thetaI;
		this._thetaJ = other._thetaJ;
		this._thetaK = other._thetaK;
		this._type = other._type;
	}

	/**
	 * Converts the angles stored within to radians and returns the
	 * result in a new Angles3D
	 * 
	 * @return
	 */
	public Angles3D toRadians(){
		if(_type == AngleType.RADIANS){
			// we're already in degrees.
			return new Angles3D(this);
		}
		
		Angles3D ret = new Angles3D();
		
		ret._thetaI = (_thetaI * TO_RADIANS) % TWO_PI;
		ret._thetaJ = (_thetaJ * TO_RADIANS) % TWO_PI;
		ret._thetaK = (_thetaK * TO_RADIANS) % TWO_PI;
		ret._type = AngleType.RADIANS;
		
		return ret;
	}
	
	/**
	 * Converts the angles stored within to degrees and returns the
	 * result in a new Angles3D
	 * @return
	 */
	public Angles3D toDegrees(){
		if(_type == AngleType.DEGREES){
			// we're already in degrees
			return new Angles3D(this);
		}
		
		Angles3D ret = new Angles3D();
		
		ret._thetaI = (_thetaI * TO_DEGREES) % 360.;
		ret._thetaJ = (_thetaJ * TO_DEGREES) % 360.;
		ret._thetaK = (_thetaK * TO_DEGREES) % 360.;
		ret._type = AngleType.DEGREES;
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Angles3D [_thetaI=" + _thetaI + ", _thetaJ=" + _thetaJ
				+ ", _thetaK=" + _thetaK + ", _type=" + _type + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(_thetaI);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(_thetaJ);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(_thetaK);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((_type == null) ? 0 : _type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Angles3D other = (Angles3D) obj;
		if (Double.doubleToLongBits(_thetaI) != Double
				.doubleToLongBits(other._thetaI))
			return false;
		if (Double.doubleToLongBits(_thetaJ) != Double
				.doubleToLongBits(other._thetaJ))
			return false;
		if (Double.doubleToLongBits(_thetaK) != Double
				.doubleToLongBits(other._thetaK))
			return false;
		if (_type != other._type)
			return false;
		return true;
	}

	/**
	 * @return the _thetaI
	 */
	public double get_thetaI() {
		return _thetaI;
	}

	/**
	 * @param _thetaI the _thetaI to set
	 */
	public void set_thetaI(double _thetaI) {
		this._thetaI = _thetaI;
	}

	/**
	 * @return the _thetaJ
	 */
	public double get_thetaJ() {
		return _thetaJ;
	}

	/**
	 * @param _thetaJ the _thetaJ to set
	 */
	public void set_thetaJ(double _thetaJ) {
		this._thetaJ = _thetaJ;
	}

	/**
	 * @return the _thetaK
	 */
	public double get_thetaK() {
		return _thetaK;
	}

	/**
	 * @param _thetaK the _thetaK to set
	 */
	public void set_thetaK(double _thetaK) {
		this._thetaK = _thetaK;
	}

	/**
	 * @return the _type
	 */
	public AngleType get_type() {
		return _type;
	}

	/**
	 * @param _type the _type to set
	 */
	public void set_type(AngleType _type) {
		this._type = _type;
	}
	
}

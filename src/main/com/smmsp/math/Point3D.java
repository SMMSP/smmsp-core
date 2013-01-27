/*
 * Point3D.java
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
 * Represents a point in three dimensional space using double
 * precision
 * @author sean
 *
 */
public class Point3D {

	/**
	 * Point in the I direction
	 */
	protected double i = 0;
	
	/**
	 * Point in the J direction 
	 */
	protected double j = 0;
	
	/**
	 * Point in the K direction 
	 */
	protected double k = 0;
	
	/**
	 *  Initializes a point in three dimensional space at the origin.
	 */
	public Point3D(){
		// do nothing.
	}
	
	
	/**
	 * Creates a point in three dimensional space.
	 * @param _i
	 * @param _j 
	 * @param _k
	 */
	public Point3D(
			final double _i, 
			final double _j, 
			final double _k) {
		this.i = _i;
		this.j = _j;
		this.k = _k;
	}

	/**
	 * Copy constructor
	 * @param other
	 */
	public Point3D(final Point3D other){
		this.i = other.i;
		this.j = other.j;
		this.k = other.k;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vector3D [_i=" + i + ", _j=" + j + ", _k=" + k + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(i);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(j);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(k);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		final Point3D other = (Point3D) obj;
		if (Double.doubleToLongBits(i) != Double.doubleToLongBits(other.i)){
			return false;
		}
		if (Double.doubleToLongBits(j) != Double.doubleToLongBits(other.j)){
			return false;
		}
		if (Double.doubleToLongBits(k) != Double.doubleToLongBits(other.k)){
			return false;
		}
		return true;
	}

	/**
	 * @return the _i
	 */
	public double getI() {
		return i;
	}

	/**
	 * @param _i the _i to set
	 */
	public void setI(final double _i) {
		this.i = _i;
	}

	/**
	 * @return the _j
	 */
	public double getJ() {
		return j;
	}

	/**
	 * @param _j the _j to set
	 */
	public void setJ(final double _j) {
		this.j = _j;
	}

	/**
	 * @return the _k
	 */
	public double getK() {
		return k;
	}

	/**
	 * @param _k the _k to set
	 */
	public void setK(final double _k) {
		this.k = _k;
	}
	
}

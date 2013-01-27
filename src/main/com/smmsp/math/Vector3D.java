/*
 * Vector3D.java
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

import com.smmsp.math.Angles3D.AngleType;

/**
 * This math class projects a vector into a three dimensional plane.
 * This implementation utilizes double precision.
 * @author sean
 *
 */
public class Vector3D extends Point3D{

	/**
	 * Basic constructor, initializes a unit vector of 0,0,0
	 */
	public Vector3D(){
		i = j = k = 0;
	}
	
	/**
	 * Constructor.
	 * @param i
	 * @param j
	 * @param k
	 */
	public Vector3D(final double i, final double j, final double k){
		super(i, j, k);
	}
	
	/**
	 * Copy constructor
	 * @param other
	 */
	public Vector3D(final Vector3D other){
		super(other);
	}
	
	/**
	 * Adds vector to this one, returning the result.
	 * @param first
	 * @param second
	 * @return The sum of first and second.
	 */
	public Vector3D add(final Vector3D vector){
		final Vector3D ret = new Vector3D();
		
		ret.i = vector.i + i;
		ret.j = vector.j + j;
		ret.k = vector.k + k;
		
		return ret;
	}
	
	/**
	 * Subtracts the vector from this one and returns the result.
	 * @param first
	 * @param second
	 * @return
	 */
	public Vector3D subtract(final Vector3D vector){
		final Vector3D ret = new Vector3D();
		
		ret.i = i - vector.i;
		ret.j = j - vector.j;
		ret.k = k - vector.k;
		
		return ret;
	}
	
	/**
	 * Inverts the magnitudes of this vector and returns the result
	 * @param vector
	 * @return
	 */
	public Vector3D invert(){
		final Vector3D ret = new Vector3D();
		
		ret.i = -1 * i;
		ret.j = -1 * j;
		ret.k = -1 * k;
		
		return ret;
	}
	
	/**
	 * Returns the cartesian magnitude (distance from origin of the 
	 * plane to the point specified by this vector).
	 * @return
	 */
	public double magnitude(){
		final double i2 = i * i;
		final double j2 = j * j;
		final double k2 = k * k;
		
		return Math.sqrt(i2 + j2 + k2);
	}

	/**
	 * Computes the angles of the direction of this vector from
	 * the origin.  The returned Angle3D is represented in Radians.
	 * @return
	 */
	public Angles3D directionAnglesRadians(){
		final double mag = magnitude();
		
		final double aci = Math.acos(i / mag);
		final double acj = Math.acos(j / mag);
		final double ack = Math.acos(k / mag);
		
		return new Angles3D(aci, acj, ack, AngleType.RADIANS);
	}
	
	/**
	 * Computes the angles of the direction of this vector from
	 * the origin.  The returned Angle3D is represented in Degrees.
	 * @return
	 */
	public Angles3D directionAnglesDegrees(){
		final double mag = magnitude();
		
		final double aci = Math.acos(i / mag) * Angles3D.TO_DEGREES;
		final double acj = Math.acos(j / mag) * Angles3D.TO_DEGREES;
		final double ack = Math.acos(k / mag) * Angles3D.TO_DEGREES;
		
		return new Angles3D(aci, acj, ack);
	}
	
	/**
	 * Computes the dot product between this vector and the vector
	 * specified by 'Other'. 
	 * @param other
	 * @return
	 */
	public double dotProduct(final Vector3D other){
		final double ix = i * other.i;
		final double jx = j * other.j;
		final double kx = k * other.k;
		
		return ix + jx + kx;
	}
	
	/**
	 * Computes the cross product of this vector and the other vector.
	 * Computes ThisVector X OtherVector
	 * 
	 * @param other
	 * @return
	 */
	public Vector3D crossProduct(Vector3D other){
		final double ix = j * other.k - k * other.j;
		final double jx = k * other.i - i * other.k;
		final double kx = i * other.j - j * other.i;
		
		return new Vector3D(ix, jx, kx);
	}
	
	/**
	 * Returns the angle between the two vectors in Radians
	 * @param other
	 * @return
	 */
	public double angleBetweenInRadians(final Vector3D other){
		final double dotP = dotProduct(other);
		final double magx = magnitude() * other.magnitude();
		
		return Math.acos(dotP / magx);
	}
	
	/**
	 * Returns the angle between the two vectors in Degrees
	 * @param other
	 * @return
	 */
	public double angleBetweenInDegrees(final Vector3D other){
		return angleBetweenInRadians(other) * Angles3D.TO_DEGREES;
	}
	
	/**
	 * Scalar projection of this vector on top of the other vector.
	 * 
	 * @param other
	 * @return
	 */
	public double projectOnTo(final Vector3D other){
		final double dotP = dotProduct(other);
		final double magO = other.magnitude();
		
		return dotP/magO;
	}
}

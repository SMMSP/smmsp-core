/*
 * Ellipsoid.java
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
package com.smmsp.geo;

/**
 * Represents an Ellipsoid
 * 
 * @author sean
 */
public interface Ellipsoid
{
	
	/**
	 * Short name of the Ellipsoid
	 * @return
	 */
	public String getName();
	
	/**
	 * Full descriptive name of the Ellipsoid
	 * @return
	 */
	public String getFullName();
	
	/**
	 * Semi Major Axis of the Ellipsoid
	 * @return
	 */
	public double getSemiMajorAxis();
	
	/**
	 * Semi Minor Axis of the Ellipsoid
	 * @return
	 */
	public double getSemiMinorAxis();
	
	/**
	 * Inverse Flattening (1/f) of the ellipsoid
	 * @return
	 */
	public double getInverseFlattening();
}

/*
 * EllipsoidTests.java
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
package com.smmsp.tests.geo;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.smmsp.geo.Ellipsoid;
import com.smmsp.geo.Ellipsoids;
import com.smmsp.geo.GeoException;

/**
 * @author sean
 *
 */
public class EllipsoidTests
{


	@Test
	public void test() throws GeoException
	{
		Collection<Ellipsoid> ellipsoids = Ellipsoids.getAllEllipsoids();
		
		for(final Ellipsoid ell : ellipsoids)
		{
			assertNotNull("Ellipsoid should not be null", ell);
			
			assertNotNull("Name should not be null", ell.getName());
			assertTrue("Name should have a length", ell.getName().length() > 0);
			
			assertNotNull("Full name should not be null", ell.getFullName());
			assertTrue("Full name should have a length", ell.getFullName().length() >0);

			assertTrue("Semimajor should be non-zero", ell.getSemiMajorAxis() != 0);
			assertTrue("Semiminor should be non-zero", ell.getSemiMinorAxis() != 0);
			assertTrue("InvFlattening should be non-zero", ell.getInverseFlattening() != 0);
		}
		
		Ellipsoid wgs84 = Ellipsoids.findByName("WGS84");
		assertNotNull(wgs84);
		
	}
}

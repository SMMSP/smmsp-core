/*
 * Point3DTests.java
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
package com.smmsp.tests.maths;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smmsp.math.Point3D;

/**
 * @author sean
 *
 */
public class Point3DTests {


	@Test
	public final void testMethods() {
		final Point3D pnt = new Point3D();
		final Point3D pnt2 = new Point3D(1, 2, 3);
		
		assertEquals(pnt.getI(), 0, 0.1);
		assertEquals(pnt.getJ(), 0, 0.1);
		assertEquals(pnt.getK(), 0, 0.1);
		assertEquals(pnt2.getI(), 1, 0.1);
		assertEquals(pnt2.getJ(), 2, 0.1);
		assertEquals(pnt2.getK(), 3, 0.1);
		assertFalse(pnt.equals(pnt2));
		
		assertNotNull(pnt.toString());
		assertTrue(pnt.toString() instanceof String);
		
		assertTrue(pnt.hashCode() > 0);
		
		pnt.setI(1);
		assertEquals(pnt.getI(), 1, 0.1);
		
		pnt.setJ(2);
		assertEquals(pnt.getJ(), 2, 0.2);
		
		pnt.setK(3);
		assertEquals(pnt.getK(), 3, 0.2);
		
		assertEquals(pnt, pnt2);
		
		final Point3D copy = new Point3D(pnt2);
		assertEquals(copy, pnt2);
	}

}

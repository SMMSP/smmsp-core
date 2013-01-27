/*
 * Angles3DTests.java
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

import org.junit.After;
import org.junit.Test;

import com.smmsp.math.Angles3D;
import com.smmsp.math.Angles3D.AngleType;

/**
 * @author sean
 *
 */
public class Angles3DTests {

	private static final double PIO2 = Math.PI / 2.;
	private static final double TRPIO2 = 3. * Math.PI / 2.;
	private static final double PI = Math.PI;
	private static final double TPI = 2. * Math.PI;
	
	private Angles3D rad = null;
	
	@After
	public void afterTest(){
		rad = null;
	}
	
	public void verifyThreeAngles(
			final double i, final double j, final double k){
		assertEquals(i, rad.getThetaI(), 0.1);
		assertEquals(j, rad.getThetaJ(), 0.1);
		assertEquals(k, rad.getThetaK(), 0.1);
	}
	
	/**
	 * Test method for {@link com.smmsp.math.Angles3D#toRadians()}.
	 */
	@Test
	public final void testToRadians() {
		final Angles3D ang = new Angles3D();
		
		rad = ang.toRadians();
		verifyThreeAngles(0, 0, 0);
		
		rad = new Angles3D(90, 0, 0).toRadians();
		verifyThreeAngles(PIO2, 0, 0);
		
		rad = new Angles3D(0, 90, 0).toRadians();
		verifyThreeAngles(0, PIO2, 0);
		
		rad = new Angles3D(0, 0, 90).toRadians();
		verifyThreeAngles(0, 0, PIO2);
		
		rad = new Angles3D(180, 180, 180).toRadians();
		verifyThreeAngles(PI, PI, PI);
		
		rad = new Angles3D(270, 270, 270).toRadians();
		verifyThreeAngles(TRPIO2, TRPIO2, TRPIO2);
		
		rad = new Angles3D(360, 360, 360).toRadians();
		verifyThreeAngles(0, 0, 0);
		
		rad = new Angles3D(450, 450, 450).toRadians();
		verifyThreeAngles(PIO2, PIO2, PIO2);
	}

	/**
	 * Test method for {@link com.smmsp.math.Angles3D#toDegrees()}.
	 */
	@Test
	public final void testToDegrees() {
		Angles3D ang = new Angles3D(0, 0, 0, AngleType.RADIANS);
		
		rad = ang;
		verifyThreeAngles(0, 0, 0);
		
		rad = ang.toDegrees();
		verifyThreeAngles(0, 0, 0);
		
		rad = new Angles3D(PIO2, 0, 0, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(90, 0, 0);
		
		rad = new Angles3D(0, PIO2, 0, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(0, 90, 0);
		
		rad = new Angles3D(0, 0, PIO2, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(0, 0, 90);
		
		rad = new Angles3D(PI, PI, PI, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(180, 180, 180);
		
		rad = new Angles3D(TRPIO2, TRPIO2, TRPIO2, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(270, 270, 270);
		
		rad = new Angles3D(TPI, TPI, TPI, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(0, 0, 0);
		
		final double exp = TPI + PIO2;
		rad = new Angles3D(exp, exp, exp, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(90, 90, 90);
	}

	/**
	 * Test method for {@link com.smmsp.math.Angles3D#toString()}.
	 */
	@Test
	public final void testToString() {
		Angles3D ang = new Angles3D();
		
		assertTrue(ang.toString() instanceof String);
		assertNotNull(ang.toString());
	}

	@Test
	public final void testBasicMethods(){
		Angles3D ang = new Angles3D(0, 0, 0);
		Angles3D ang2 = new Angles3D(1, 2, 3);
		
		assertTrue(ang.hashCode() > 0);
		assertFalse(ang.equals(ang2));
		
		ang.setThetaI(1);
		assertEquals(ang.getThetaI(), 1, 0.1);
		
		ang.setThetaJ(2);
		assertEquals(ang.getThetaJ(), 2, 0.1);
		
		ang.setThetaK(3);
		assertEquals(ang.getThetaK(), 3, 0.1);
		
		assertEquals(ang.getType(), AngleType.DEGREES);
		ang.setType(AngleType.RADIANS);
		assertEquals(ang.getType(), AngleType.RADIANS);
		
		assertFalse(ang.equals(ang2));
		ang.setType(AngleType.DEGREES);
		
		assertEquals(ang, ang2);
		
		Angles3D copy = new Angles3D(ang);
		assertEquals(copy, ang);
	}
}

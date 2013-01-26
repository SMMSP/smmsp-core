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

	private static final double PIo2 = Math.PI / 2.;
	private static final double TrPIo2 = 3. * Math.PI / 2.;
	private static final double PI = Math.PI;
	private static final double TPI = 2. * Math.PI;
	
	private Angles3D rad = null;
	
	@After
	public void afterTest(){
		rad = null;
	}
	
	public void verifyThreeAngles(double i, double j, double k){
		assertEquals(i, rad.get_thetaI(), 0.1);
		assertEquals(j, rad.get_thetaJ(), 0.1);
		assertEquals(k, rad.get_thetaK(), 0.1);
	}
	
	/**
	 * Test method for {@link com.smmsp.math.Angles3D#toRadians()}.
	 */
	@Test
	public final void testToRadians() {
		Angles3D ang = new Angles3D();
		
		rad = ang.toRadians();
		verifyThreeAngles(0, 0, 0);
		
		rad = new Angles3D(90, 0, 0).toRadians();
		verifyThreeAngles(PIo2, 0, 0);
		
		rad = new Angles3D(0, 90, 0).toRadians();
		verifyThreeAngles(0, PIo2, 0);
		
		rad = new Angles3D(0, 0, 90).toRadians();
		verifyThreeAngles(0, 0, PIo2);
		
		rad = new Angles3D(180, 180, 180).toRadians();
		verifyThreeAngles(PI, PI, PI);
		
		rad = new Angles3D(270, 270, 270).toRadians();
		verifyThreeAngles(TrPIo2, TrPIo2, TrPIo2);
		
		rad = new Angles3D(360, 360, 360).toRadians();
		verifyThreeAngles(0, 0, 0);
		
		rad = new Angles3D(450, 450, 450).toRadians();
		verifyThreeAngles(PIo2, PIo2, PIo2);
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
		
		rad = new Angles3D(PIo2, 0, 0, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(90, 0, 0);
		
		rad = new Angles3D(0, PIo2, 0, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(0, 90, 0);
		
		rad = new Angles3D(0, 0, PIo2, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(0, 0, 90);
		
		rad = new Angles3D(PI, PI, PI, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(180, 180, 180);
		
		rad = new Angles3D(TrPIo2, TrPIo2, TrPIo2, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(270, 270, 270);
		
		rad = new Angles3D(TPI, TPI, TPI, AngleType.RADIANS).toDegrees();
		verifyThreeAngles(0, 0, 0);
		
		final double exp = TPI + PIo2;
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

}

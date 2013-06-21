/*
 * SolarPlanetsTest.java
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
package com.smmsp.tests.astro;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.smmsp.astro.SolarBody;
import com.smmsp.astro.SolarBodies;

/**
 * @author sean
 *
 */
public class SolarPlanetsTest
{

	@Test
	public void test()
	{
		Collection<SolarBody> bodies = SolarBodies.getAllBodies();
		
		for(final SolarBody body : bodies)
		{
			assertNotNull(body.getName());
			assertTrue(body.getName().length() > 0);
			
			System.out.println(body);
			
			assertTrue(body.getName() + " RadiusInKM", body.getRadiusInKM() != 0);
			assertTrue(body.getName() + " getMassInKG", body.getMassInKG() != 0);
			assertTrue(body.getName() + " getSiderealRotationInDays", body.getSiderealRotationInDays() != 0);
			assertTrue(body.getName() + " getSiderealOrbitalPeriodInDays", body.getSiderealOrbitalPeriodInDays() != 0);
			assertTrue(body.getName() + " getEquatorialInclination", body.getEquatorialInclination() != 0);
			assertTrue(body.getName() + " getSemimajorOrbitalAxis", body.getSemimajorOrbitalAxis() != 0);
			assertTrue(body.getName() + " getEccentricity", body.getEccentricity() != 0);
			assertTrue(body.getName() + " getOrbitalInclination", body.getOrbitalInclination() != 0);
			assertTrue(body.getName() + " getGravitationalParameter", body.getGravitationalParameter() != 0);
			assertTrue(body.getName() + " getAphelion", body.getAphelion() != 0);
			assertTrue(body.getName() + " getPerihelion", body.getPerihelion() != 0);
			assertTrue(body.getName() + " getOrbitalPeriodInDays", body.getOrbitalPeriodInDays() != 0);
			assertTrue(body.getName() + " semimajor axis", body.getSemimajorBodyAxis() != 0);
			assertTrue(body.getName() + " semiminor axis", body.getSemiminorBodyAxis() != 0);
		}
	}
	
}

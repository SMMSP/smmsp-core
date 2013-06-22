/*
 * UniversalTransverseMercator.java
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

import org.apache.log4j.Logger;

/**
 * Implements the Universal Transverse Mercator algorithm as 
 * described in DMA Technical Manual 8358.2 THE UNIVERSAL GRIDS:
 * Universal Transverse Mercator (UTM) published in September 1989
 * by the Defense Mapping Agency
 * 
 * @author sean
 */
public abstract class UniversalTransverseMercator
{

	/**
	 * Some precalculated constants
	 */
	protected static final double	CONST_315_512		= 315. / 512.;
	protected static final double	CONST_11_16			= 11. / 16.;
	protected static final double	CONST_35_48			= 35. / 48.;
	protected static final double	CONST_3_4			= 3. / 4.;
	protected static final double	CONST_15_16			= 15. / 16.;
	protected static final double	CONST_55_64			= 55. / 64.;
	protected static final double	CONST_7_8			= 7. / 8.;
	protected static final double	CONST_3_2			= 3. / 2.;
	protected static final double	CONST_5_4			= 5. / 4.;
	protected static final double	CONST_81_64			= 81. / 64.;

	protected static final Logger	LOG					= Logger.getLogger(UniversalTransverseMercator.class);

	/**
	 * Central scale factor, an arbitrary reduction applied to all geodetic
	 * lengths to reduce the maximum scale distortion of the projection
	 */
	protected static final double	k0					= 0.9996;

	/**
	 * Some UTM constants
	 */
	protected static final long		NORTHERN_NORTHING	= 0;
	protected static final long		SOUTHERN_NORTHING	= 10000000;
	protected static final long		FALSE_EASTING		= 500000;

	/**
	 * The default solar body (Earth) :)
	 */
	protected static Ellipsoid		defaultEllipsoid;
	protected static double			SEMIMAJOR_AXIS;
	protected static double			SEMIMINOR_AXIS;

	static
	{
		try
		{
			defaultEllipsoid = Ellipsoids.findByName("WGS84");
			LOG.debug("Default Ellipsoid -> " + defaultEllipsoid);

			// need to upconvert both to meters.
			SEMIMAJOR_AXIS = defaultEllipsoid.getSemiMajorAxis();
			SEMIMINOR_AXIS = defaultEllipsoid.getSemiMinorAxis();
		} catch (GeoException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Private - static only methods.
	 */
	private UniversalTransverseMercator()
	{
		// not used.
	}

	/**
	 * Converts Geographic Coordinates (Latitude, Longitude) to UTM Grid
	 * Coordinates
	 * 
	 * @param latitude
	 *            - in degrees
	 * @param longitude
	 *            - in degrees
	 * @return GridCoordinate.Double
	 * @throws GeoException
	 */
	public static GridCoordinate.Double convertGeographicToUTMGrid(
			final double latitude, final double longitude) throws GeoException
	{
		if (latitude >= 84 || latitude <= -80)
		{
			// Latitude limits are 84°N and -80°S
			throw new GeoException(
					"Latitude must be -80° <= X <= 84°, but was: '"
							+ latitude
							+ "' Consider using a UniversalPolarStereographic instead.");
		}

		final double a = SEMIMAJOR_AXIS;
		final double b = SEMIMINOR_AXIS;

		final double latr = Math.toRadians(latitude);
		final double lonr = Math.toRadians(longitude);

		// zone = 1 + Math.floor((longitude+180)/6)
		final int zone = 1 + (int) Math.floor((longitude + 180.) / 6.);

		final double sinlat = Math.sin(latr);
		final double coslat = Math.cos(latr);
		final double cos2lat = coslat * coslat;
		final double cos4lat = cos2lat * cos2lat;
		final double cos6lat = cos4lat * cos2lat;
		final double tanlat = Math.tan(latr);
		final double tan2lat = tanlat * tanlat;
		final double tan4lat = tan2lat * tan2lat;
		final double e2prime = (a * a - b * b) / (b * b);
		final double e4prime = e2prime * e2prime;

		final double radofcurv = getRadiusOfCurvature(latr);

		final double vsincoslat = radofcurv * k0 * sinlat * coslat;
		final double vcoslat = radofcurv * k0 * coslat;
		final double lon0 = Math.toRadians(3. + 6. * (zone - 1.) - 180.);
		final double dlon = lonr - lon0;
		final double dlon2 = dlon * dlon;
		final double dlon4 = dlon2 * dlon2;

		final double T1 = getMeridionalArc(latr) * k0;

		final double T2 = (vsincoslat) / 2.;

		final double T3 = (vsincoslat * cos2lat)
				/ 24.
				* (5. - tanlat * tanlat + 9. * e2prime * cos2lat + 4. * e2prime
						* e2prime * cos4lat);

		final double T4 = (vsincoslat * cos4lat)
				/ 720.
				* (61. - 51. * tan2lat + tan2lat * tan2lat + 270. * e2prime
						* cos2lat - 330. * tan2lat * e2prime * cos2lat + 445.
						* e4prime * cos4lat + 324. * e2prime * e4prime
						* cos6lat - 680. * tan2lat * e4prime * cos4lat + 88.
						* e4prime * e4prime * cos4lat * cos4lat - 600.
						* tan2lat * e4prime * e2prime * cos6lat - 192.
						* tan2lat * e4prime * e4prime * cos4lat * cos4lat);

		final double T5 = (vsincoslat * cos6lat)
				/ 40320.
				* (1385. - 3111. * tan2lat + 543. * tan4lat - tan4lat * tan2lat);

		final double T6 = vcoslat;

		final double T7 = (vcoslat * cos2lat) / 6.
				* (1. - tan2lat + e2prime * cos2lat);

		final double T8 = (vcoslat * cos4lat)
				/ 120.
				* (5. - 18. * tan2lat + tan4lat + 14. * e2prime * cos2lat - 58.
						* tan2lat * e2prime * cos2lat + 13. * e4prime * cos4lat
						+ 4. * e4prime * e2prime * cos6lat - 64. * tan2lat
						* e4prime * cos4lat - 24. * tan2lat * e4prime * e2prime
						* cos6lat);

		final double T9 = (vcoslat * cos6lat) / 5040.
				* (61. - 479. * tan2lat + 179. * tan4lat - tan4lat * tan2lat);

		final double T26 = cos2lat / 2. * (1. + e2prime * cos2lat);

		final double T27 = cos4lat
				/ 24.
				* (5. - 4. * tan2lat + 14. * e2prime * cos2lat + 13. * e4prime
						* cos4lat - 28. * tan2lat * e2prime * cos2lat + 4.
						* e4prime * e2prime * cos6lat - 48. * tan2lat * e4prime
						* cos4lat - 24. * tan2lat * e4prime * e2prime * cos6lat);

		final double T28 = cos6lat / 720.
				* (61. - 148. * tan2lat + 16. * tan4lat);

		final double FN = (latitude < 0) ? SOUTHERN_NORTHING
				: NORTHERN_NORTHING;

		final double FE = FALSE_EASTING;

		// Northing = FN + (T1 + ∆λ^2 * T2 + ∆λ^4 * T3 + ∆λ^6 * T4 + ∆λ^8 * T5)
		final double northing = FN
				+ (T1 + dlon2 * T2 + dlon4 * T3 + dlon4 * dlon2 * T4 + dlon4
						* dlon4 * T5);

		// Easting = FE + (∆λ * T6 + ∆λ^3 * T7 + ∆λ^5 * T8 + ∆λ^7 * T9 )
		final double easting = FE
				+ (dlon * T6 + dlon2 * dlon * T7 + dlon4 * dlon * T8 + dlon4
						* dlon2 * dlon * T9);

		// scale factor = k0 * (1 + ∆λ^2 * T26 + ∆λ^4 * T27 + ∆λ^6 * T28)
		final double scaleFactor = k0
				* (1. + dlon2 * T26 + dlon4 * T27 + dlon2 * dlon4 * T28);

		return new GridCoordinate.Double(northing, easting, zone, scaleFactor);
	}

	/**
	 * Returns the meridional arc, the true meridional distance on the ellipsoid
	 * form the equator.
	 * 
	 * @param latitude
	 *            - in radians
	 * @return meridional arc length
	 */
	protected static double getMeridionalArc(final double latitude)
	{
		final double a = SEMIMAJOR_AXIS;
		final double b = SEMIMINOR_AXIS;
		final double n = (a - b) / (a + b);
		final double n2 = n * n;
		final double n3 = n2 * n;
		final double n4 = n2 * n2;

		final double Aprime = a
				* (1 - n + CONST_5_4 * (n2 - n3) + CONST_81_64 * (n4 - n2 * n3));
		final double Bprime = CONST_3_2 * a
				* (n - n2 + CONST_7_8 * (n3 - n4) + CONST_55_64 * (n3 * n2));
		final double Cprime = CONST_15_16 * a
				* (n2 - n3 + CONST_3_4 * (n4 - n3 * n2));
		final double Dprime = CONST_35_48 * a
				* (n3 - n4 + CONST_11_16 * n3 * n2);
		final double Eprime = CONST_315_512 * a * (n4 - n3 * n2);

		return Aprime * latitude - Bprime * Math.sin(2. * latitude) + Cprime
				* Math.sin(4. * latitude) - Dprime * Math.sin(6. * latitude)
				+ Eprime * Math.sin(8. * latitude);
	}

	/**
	 * Returns the radius of curvature in the prime vertical; also defined as
	 * the normal to the ellipsoid terminating at the minor axis
	 * 
	 * @param latitude
	 *            - in radians
	 * @return
	 */
	protected static double getRadiusOfCurvature(final double latitude)
	{
		final double a = SEMIMAJOR_AXIS;
		final double b = SEMIMINOR_AXIS;
		final double e2 = (a * a - b * b) / (a * a);
		final double sin = Math.sin(latitude);
		final double denom = Math.sqrt(1. - e2 * sin * sin);

		return a / denom;
	}

	/**
	 * Sets the default solar body - used for when you're not on Earth.
	 * 
	 * @param body
	 *            The SolarBody to project upon.
	 */
	public static void setDefaultEllipsoid(final Ellipsoid ellipse)
	{
		defaultEllipsoid = ellipse;
		SEMIMAJOR_AXIS = ellipse.getSemiMajorAxis();
		SEMIMINOR_AXIS = ellipse.getSemiMinorAxis();
	}
}

/*
 * SolarBody.java
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
package com.smmsp.astro;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Parameters representing a single body in the solar system.
 * 
 * @author sean
 */
@XmlRootElement(name="body")
public class SolarBodyImpl implements SolarBody
{
	/**
	 * The name of the body
	 */
	@XmlAttribute(name="name")
	protected String name;
	
	/**
	 * Mean radius of the body in KM 
	 */
	@XmlElement
	protected double radiusInKM;
	
	/**
	 * Mass of the body in KG
	 */
	@XmlElement
	protected double massInKG;
	
	/**
	 * Sidereal rotation of the body in days (a day on the body)
	 */
	@XmlElement
	protected double siderealRotationInDays;
	
	/**
	 * Inclination of the equator above the orbital plane (axial tilt)
	 */
	@XmlElement
	protected double equatorialInclination;
	
	/**
	 * Semimajor axis of the orbit of the body (in KM)
	 */
	@XmlElement
	protected double semimajorOrbitalAxis;
	
	/**
	 * Eccentricity of the orbit of the body
	 */
	@XmlElement
	protected double eccentricity;
	
	/**
	 * Inclination of the orbit above the solar plane
	 */
	@XmlElement
	protected double orbitalInclination;
	
	/**
	 * Sidereal period of the orbit in days
	 */
	@XmlElement
	protected double siderealOrbitalPeriodInDays;
	
	/**
	 * Gravitational parameter of the body
	 */
	@XmlElement
	protected double gravitationalParameter;
	
	/**
	 * Orbital aphelion of the body KM
	 */
	@XmlElement
	protected double aphelion;
	
	/**
	 * Orbital perihelion of the body in KM
	 */
	@XmlElement
	protected double perihelion;
	
	/**
	 * Tropical orbital period of the body in days
	 */
	@XmlElement
	protected double orbitalPeriodInDays;
	
	/**
	 * Semimajor axis of the body in KM
	 */
	@XmlElement
	protected double semimajorBodyAxis;
	
	/**
	 * Semiminor axis of the body in KM
	 */
	@XmlElement
	protected double semiminorBodyAxis;
	
	
	private SolarBodyImpl()
	{
		// empty for xml loading
	}

	/**
	 * @return the radiusInKM
	 */
	public double getRadiusInKM()
	{
		return radiusInKM;
	}

	/**
	 * @return the massInKG
	 */
	public double getMassInKG()
	{
		return massInKG;
	}

	/**
	 * @return the siderealRotationInDays
	 */
	public double getSiderealRotationInDays()
	{
		return siderealRotationInDays;
	}

	/**
	 * @return the equatorialInclination
	 */
	public double getEquatorialInclination()
	{
		return equatorialInclination;
	}

	/**
	 * @return the semimajorOrbitalAxis
	 */
	public double getSemimajorOrbitalAxis()
	{
		return semimajorOrbitalAxis;
	}

	/**
	 * @return the eccentricity
	 */
	public double getEccentricity()
	{
		return eccentricity;
	}

	/**
	 * @return the orbitalInclination
	 */
	public double getOrbitalInclination()
	{
		return orbitalInclination;
	}

	/**
	 * @return the siderealOrbitalPeriodInDays
	 */
	public double getSiderealOrbitalPeriodInDays()
	{
		return siderealOrbitalPeriodInDays;
	}

	/**
	 * @return the gravitationalParameter
	 */
	public double getGravitationalParameter()
	{
		return gravitationalParameter;
	}

	/**
	 * @return the aphelion
	 */
	public double getAphelion()
	{
		return aphelion;
	}

	/**
	 * @return the perihelion
	 */
	public double getPerihelion()
	{
		return perihelion;
	}

	/**
	 * @return the orbitalPeriodInDays
	 */
	public double getOrbitalPeriodInDays()
	{
		return orbitalPeriodInDays;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the semimajorBodyAxis
	 */
	public double getSemimajorBodyAxis()
	{
		return semimajorBodyAxis;
	}

	/**
	 * @return the semiminorBodyAxis
	 */
	public double getSemiminorBodyAxis()
	{
		return semiminorBodyAxis;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(aphelion);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(eccentricity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(equatorialInclination);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(gravitationalParameter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(massInKG);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(orbitalInclination);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(orbitalPeriodInDays);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(perihelion);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radiusInKM);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(semimajorBodyAxis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(semimajorOrbitalAxis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(semiminorBodyAxis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(siderealOrbitalPeriodInDays);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(siderealRotationInDays);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof SolarBodyImpl))
		{
			return false;
		}
		SolarBodyImpl other = (SolarBodyImpl) obj;
		if (Double.doubleToLongBits(aphelion) != Double
				.doubleToLongBits(other.aphelion))
		{
			return false;
		}
		if (Double.doubleToLongBits(eccentricity) != Double
				.doubleToLongBits(other.eccentricity))
		{
			return false;
		}
		if (Double.doubleToLongBits(equatorialInclination) != Double
				.doubleToLongBits(other.equatorialInclination))
		{
			return false;
		}
		if (Double.doubleToLongBits(gravitationalParameter) != Double
				.doubleToLongBits(other.gravitationalParameter))
		{
			return false;
		}
		if (Double.doubleToLongBits(massInKG) != Double
				.doubleToLongBits(other.massInKG))
		{
			return false;
		}
		if (name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		} else if (!name.equals(other.name))
		{
			return false;
		}
		if (Double.doubleToLongBits(orbitalInclination) != Double
				.doubleToLongBits(other.orbitalInclination))
		{
			return false;
		}
		if (Double.doubleToLongBits(orbitalPeriodInDays) != Double
				.doubleToLongBits(other.orbitalPeriodInDays))
		{
			return false;
		}
		if (Double.doubleToLongBits(perihelion) != Double
				.doubleToLongBits(other.perihelion))
		{
			return false;
		}
		if (Double.doubleToLongBits(radiusInKM) != Double
				.doubleToLongBits(other.radiusInKM))
		{
			return false;
		}
		if (Double.doubleToLongBits(semimajorBodyAxis) != Double
				.doubleToLongBits(other.semimajorBodyAxis))
		{
			return false;
		}
		if (Double.doubleToLongBits(semimajorOrbitalAxis) != Double
				.doubleToLongBits(other.semimajorOrbitalAxis))
		{
			return false;
		}
		if (Double.doubleToLongBits(semiminorBodyAxis) != Double
				.doubleToLongBits(other.semiminorBodyAxis))
		{
			return false;
		}
		if (Double.doubleToLongBits(siderealOrbitalPeriodInDays) != Double
				.doubleToLongBits(other.siderealOrbitalPeriodInDays))
		{
			return false;
		}
		if (Double.doubleToLongBits(siderealRotationInDays) != Double
				.doubleToLongBits(other.siderealRotationInDays))
		{
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SolarBody [name=");
		builder.append(name);
		builder.append(", radiusInKM=");
		builder.append(radiusInKM);
		builder.append(", massInKG=");
		builder.append(massInKG);
		builder.append(", siderealRotationInDays=");
		builder.append(siderealRotationInDays);
		builder.append(", equatorialInclination=");
		builder.append(equatorialInclination);
		builder.append(", semimajorOrbitalAxis=");
		builder.append(semimajorOrbitalAxis);
		builder.append(", eccentricity=");
		builder.append(eccentricity);
		builder.append(", orbitalInclination=");
		builder.append(orbitalInclination);
		builder.append(", siderealOrbitalPeriodInDays=");
		builder.append(siderealOrbitalPeriodInDays);
		builder.append(", gravitationalParameter=");
		builder.append(gravitationalParameter);
		builder.append(", aphelion=");
		builder.append(aphelion);
		builder.append(", perihelion=");
		builder.append(perihelion);
		builder.append(", orbitalPeriodInDays=");
		builder.append(orbitalPeriodInDays);
		builder.append(", semimajorBodyAxis=");
		builder.append(semimajorBodyAxis);
		builder.append(", semiminorBodyAxis=");
		builder.append(semiminorBodyAxis);
		builder.append("]");
		return builder.toString();
	}


}
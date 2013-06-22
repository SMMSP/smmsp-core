/*
 * Ellipsoids.java
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.smmsp.astro.SolarBodies;

/**
 * Manager class for ellipsoids.  Reads them from /ellipsoids/*.xml
 * 
 * @author sean
 */
public abstract class Ellipsoids
{

	/**
	 * Logger
	 */
	protected static Logger					LOG			= Logger.getLogger(Ellipsoids.class);

	/**
	 * The list of available solar bodies
	 */
	protected static Map<String, Ellipsoid>	ellipsoids	= null;
	static
	{
		try
		{
			reloadInitialBodies();
		} catch (GeoException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Private & Empty - utility storage class
	 */
	private Ellipsoids()
	{
		// private and empty
	}

	/**
	 * Searches for a Ellipsoid and returns an Ellipsoid object if its found.
	 * Null if not available .
	 * 
	 * @param name
	 *            Name of the ellipsoid
	 * @return Ellipsoid mapped to that name
	 * @throws GeoException
	 */
	public static Ellipsoid findByName(final String name) throws GeoException
	{
		if (ellipsoids == null || ellipsoids.isEmpty())
		{
			throw new GeoException(
					"No Ellipsoids available.  Did you forget to load some?");
		}

		return ellipsoids.get(name.trim().toLowerCase());
	}

	/**
	 * Returns a list of all Ellipsoids available
	 */
	public static Collection<Ellipsoid> getAllEllipsoids()
	{
		return Collections.unmodifiableCollection(ellipsoids.values());
	}

	/**
	 * Loads some ellipsoids from an XML file.
	 * 
	 * @param path
	 *            The path to the XML file
	 * @throws GeoException
	 *             upon a parsing exception.
	 */
	public static void loadFromXML(final Path path) throws GeoException
	{
		try
		{
			final InputStream is = Files.newInputStream(path,
					StandardOpenOption.READ);
			final JAXBContext ctx = JAXBContext
					.newInstance(EllipsoidsList.class);
			final Unmarshaller unmarsh = ctx.createUnmarshaller();
			final EllipsoidsList el = (EllipsoidsList) unmarsh.unmarshal(is);

			if (ellipsoids == null)
			{
				ellipsoids = new HashMap<String, Ellipsoid>();
			}

			for (final Ellipsoid ellipsoid : el.ellipsoids)
			{
				LOG.debug("Found ellipsoid: " + ellipsoid.getName());
				ellipsoids.put(ellipsoid.getName().trim().toLowerCase(),
						ellipsoid);
			}

		} catch (JAXBException e)
		{
			LOG.error("JAXB XML error", e);
		} catch (IOException e)
		{
			LOG.error("IO Exception", e);
		}
	}

	/**
	 * (Re)Loads the initial ellipsoids from the /ellipsoids/ folder.
	 * 
	 * @throws GeoException
	 */
	protected static void reloadInitialBodies() throws GeoException
	{
		try
		{
			final URL url = SolarBodies.class.getResource("/ellipsoids/");
			final URI uri = url.toURI();
			final Path path = Paths.get(uri);
			final DirectoryStream<Path> ds = Files.newDirectoryStream(path,
					"*.xml");
			final Iterator<Path> rator = ds.iterator();
			while (rator.hasNext())
			{
				loadFromXML(rator.next());
			}
		} catch (IOException e)
		{
			LOG.error(e);
		} catch (URISyntaxException e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Internal class for XML deserialization
	 * 
	 * @author sean
	 * 
	 */
	@XmlRootElement(name = "ellipsoids")
	protected static class EllipsoidsList
	{
		@XmlElement(name = "ellipsoid")
		protected List<EllipsoidImpl>	ellipsoids;
	}

	/**
	 * Internal implementation of the Ellipsoid
	 * 
	 * @author sean
	 */
	@XmlRootElement(name = "ellipsoid")
	protected static class EllipsoidImpl implements Ellipsoid
	{
		@XmlAttribute
		protected String	name;

		@XmlElement(name = "full_name")
		protected String	fullName;

		@XmlElement(name = "semimajor_axis")
		protected double	semiMajorAxis;

		@XmlElement(name = "semiminor_axis")
		protected double	semiMinorAxis;

		@XmlElement
		protected double	inverseFlattening;

		private EllipsoidImpl()
		{
			// not needed - only for jaxb
		}

		/**
		 * @return the name
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * @return the fullName
		 */
		public String getFullName()
		{
			return fullName;
		}

		/**
		 * @return the semiMajorAxis
		 */
		public double getSemiMajorAxis()
		{
			return semiMajorAxis;
		}

		/**
		 * @return the semiMinorAxis
		 */
		public double getSemiMinorAxis()
		{
			return semiMinorAxis;
		}

		/**
		 * @return the inverseFlattening
		 */
		public double getInverseFlattening()
		{
			return inverseFlattening;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((fullName == null) ? 0 : fullName.hashCode());
			long temp;
			temp = Double.doubleToLongBits(inverseFlattening);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			temp = Double.doubleToLongBits(semiMajorAxis);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(semiMinorAxis);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
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
			if (!(obj instanceof EllipsoidImpl))
			{
				return false;
			}
			EllipsoidImpl other = (EllipsoidImpl) obj;
			if (fullName == null)
			{
				if (other.fullName != null)
				{
					return false;
				}
			} else if (!fullName.equals(other.fullName))
			{
				return false;
			}
			if (Double.doubleToLongBits(inverseFlattening) != Double
					.doubleToLongBits(other.inverseFlattening))
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
			if (Double.doubleToLongBits(semiMajorAxis) != Double
					.doubleToLongBits(other.semiMajorAxis))
			{
				return false;
			}
			if (Double.doubleToLongBits(semiMinorAxis) != Double
					.doubleToLongBits(other.semiMinorAxis))
			{
				return false;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("EllipsoidImpl [name=");
			builder.append(name);
			builder.append(", fullName=");
			builder.append(fullName);
			builder.append(", semiMajorAxis=");
			builder.append(semiMajorAxis);
			builder.append(", semiMinorAxis=");
			builder.append(semiMinorAxis);
			builder.append(", inverseFlattening=");
			builder.append(inverseFlattening);
			builder.append("]");
			return builder.toString();
		}

	}
}

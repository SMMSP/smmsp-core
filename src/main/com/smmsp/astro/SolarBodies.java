/*
 * SolarPlanets.java
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

/**
 * Interface 
 * @author sean
 *
 */
public class SolarBodies
{
	/**
	 * Logger
	 */
	protected static Logger LOG = Logger.getLogger(SolarBodies.class);
	
	/**
	 * The list of available solar bodies
	 */
	protected static Map<String, SolarBody> solarBodies = null;
	
	static
	{
		try
		{
			reloadInitialBodies();
		} catch (AstroException e)
		{
			LOG.error(e);
		}
	}
	
	/**
	 * private - non instantiable
	 */
	private SolarBodies()
	{
		// no instantiation
	}

	/**
	 * Searches for a solar body and returns a solar body object if 
	 * its found.  Null if not available.
	 * @param name Name of the solar body
	 * @return SolarBody mapped to that name
	 * @throws AstroException
	 */
	public static SolarBody findByName(final String name) throws AstroException
	{
		if(solarBodies == null || solarBodies.isEmpty())
		{
			throw new AstroException(
					"No solar bodies available.  Did you forget to load some?");
		}
		
		return solarBodies.get(name.trim().toLowerCase());
	}
	
	/**
	 * Returns a list of all Solar bodies available
	 */
	public static Collection<SolarBody> getAllBodies() 
	{
		return Collections.unmodifiableCollection(solarBodies.values());
	}
	
	/**
	 * Loads some SolarBodies from an XML file.
	 * 
	 * @param path The path to the XML file
	 * @throws AstroException upon a parsing exception.
	 */
	public static void loadFromXML(final Path path) throws AstroException
	{
		try {
			final InputStream is = Files.newInputStream(path,
					StandardOpenOption.READ
					);
			final JAXBContext ctx = JAXBContext.newInstance(SolarPlanetsList.class);
			final Unmarshaller unmarsh = ctx.createUnmarshaller();
			final SolarPlanetsList spl = (SolarPlanetsList) unmarsh.unmarshal(is);
			
			if(solarBodies == null)
			{
				solarBodies = new HashMap<String, SolarBody>();
			}
			
			for(final SolarBody body : spl.bodies)
			{
				LOG.debug("Found body: " + body.name);
				solarBodies.put(body.name, body);
			}
			
		} catch (JAXBException e) {
			LOG.error("JAXB XML error", e);
		} catch (IOException e) {
			LOG.error("IO Exception", e);
		}
	}
	
	/**
	 * (Re)Loads the initial bodies from the /bodies/ folder.
	 * 
	 * @throws AstroException
	 */
	protected static void reloadInitialBodies() throws AstroException
	{
		try {
			final URL url = SolarBodies.class.getResource("/bodies/");
			final URI uri = url.toURI();
			final Path path = Paths.get(uri);
			final DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*.xml");
			final Iterator<Path> rator = ds.iterator();
			while(rator.hasNext()){
				loadFromXML(rator.next());
			}
		} catch (IOException e) {
			LOG.error(e);
		} catch (URISyntaxException e) {
			LOG.error(e);
		}
	}
	
	/**
	 * Internal class for XML deserialization
	 * 
	 * @author sean
	 *
	 */
	@XmlRootElement(name="bodies")
	protected static class SolarPlanetsList
	{
		@XmlElement(name="body")
		protected List<SolarBody> bodies;
	}
	
}

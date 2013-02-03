/*
 * TLESetList.java
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
package com.smmsp.core.tle;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * This class provides an entry point for the XML TLE Set locations
 * file in resources/TLEs. 
 * @author sean
 */
@XmlRootElement(name="locations")
public class TLESetList {

	@XmlAttribute
	private String name;
	
	@XmlElement(name="location")
	private List<TLESetLocation> locations;
	
	public TLESetList(){
		// does nothing.
	}

	/**
	 * @return the locations
	 */
	public List<TLESetLocation> getLocationList() {
		return locations;
	}

	/**
	 * @param locations the locations to set
	 */

	public void setLocationList(List<TLESetLocation> locations) {
		this.locations = locations;
	}
	
	public String getName(){
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TLESetList [locations=");
		builder.append(locations);
		builder.append("]");
		return builder.toString();
	}
	
	
}

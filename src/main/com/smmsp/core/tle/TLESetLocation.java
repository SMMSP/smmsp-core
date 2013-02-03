/*
 * TLESetLocation.java
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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A TLE Set Location provides a Name, URL, and Description for a
 * NORAD Three/Two line element set.  This clas was designed to be
 * used with Celestrak TLEs, but TLEs provided on any HTTP url can
 * be used with this system.
 * 
 * @author sean
 *
 */
@XmlRootElement(name="location")
public class TLESetLocation {

	/**
	 * The Name of this Location 
	 */
	private String name;
	
	/**
	 * The URl of this location
	 */
	private String httpUrl;
	
	/**
	 * The description of this location.
	 */
	private String description;

	public TLESetLocation(){
		// does nothing
	}
	
	/**
	 * Constructor!
	 * @param name
	 * @param httpUrl
	 * @param description
	 */
	public TLESetLocation(String name, String httpUrl, String description) {
		this.name = name;
		this.httpUrl = httpUrl;
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the httpUrl
	 */
	public String getHttpUrl() {
		return httpUrl;
	}

	/**
	 * @param httpUrl the httpUrl to set
	 */
	@XmlElement
	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TLESetLocation [name=");
		builder.append(name);
		builder.append(", httpUrl=");
		builder.append(httpUrl);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
	
}

/*
 * CelestrakCacheUpdater.java
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
package com.smmsp.tests.core;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.smmsp.core.tle.CelestrackTLEs;
import com.smmsp.core.tle.TLESetList;

/**
 * @author sean
 * 
 */
public class CelestrakCacheUpdater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CelestrackTLEs.updateCache();

		try {
			InputStream is = CelestrakCacheUpdater.class
					.getResourceAsStream("/tles/SpecialInterest.xml");

			JAXBContext ctx = JAXBContext.newInstance(TLESetList.class);
			Unmarshaller unmarsh = ctx.createUnmarshaller();
			TLESetList list = (TLESetList) unmarsh.unmarshal(is);

			System.out.println(list);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}

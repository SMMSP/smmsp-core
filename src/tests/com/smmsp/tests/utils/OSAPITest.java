/*
 * OSAPITest.java
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
package com.smmsp.tests.utils;

import com.smmsp.core.utils.OSAPI;

/**
 * @author sean
 *
 */
public class OSAPITest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Properties:");
		System.getProperties().list(System.out);
		
		System.out.println();
		System.out.println("OSClass: " + OSAPI.getOSName());
		System.out.println("OSName: " + OSAPI.OS_NAME);
		System.out.println("OSVer: " + OSAPI.OS_VER);
		System.out.println("OSArch: " + OSAPI.OS_ARCH);
		System.out.println();
		System.out.println("CacheDir: " + OSAPI.getCacheDirectory());
	}

}

/*
 * KeplerianOrbit.java
 * Copyright (C) 2012 Sean P Madden
 * This program is free software: you can redistribute it and/or
 * modify
 * it under the terms of the GNU General Public License as published
 * by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 * If you would like to license this code under the GNU LGPL, please
 * see
 * http://www.seanmadden.net/licensing for details.
 */
package com.smmsp.astro;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * TODO comment this.
 * 
 * @author Sean P Madden
 */
public class KeplerianOrbit {

	private Calendar	_epoch			= new GregorianCalendar();

	private double		_inclination	= 0;

	private double		_raan			= 0;

	private double		_eccentricity	= 0;

	private double		_argOfPerigee	= 0;

	private double		_meanAnomaly	= 0;

	private double		_meanMotion		= 0;

	public synchronized double getPropogationTime(Calendar atTime) {
		double propTimeInSolarDays = 0;
		propTimeInSolarDays = atTime.getTimeInMillis()
				- _epoch.getTimeInMillis();

		// a solar day is exactly 24 hours long
		propTimeInSolarDays /= 86400000;

		return propTimeInSolarDays;
	}

	/**
	 * Returns the _epoch
	 * 
	 * @return _epoch the _epoch
	 */
	public synchronized Calendar get_epoch() {
		return _epoch;
	}

	/**
	 * Sets the _epoch
	 * 
	 * @param _epoch
	 *            the _epoch to set
	 */
	public synchronized void set_epoch(Calendar _epoch) {
		this._epoch = _epoch;
	}

	/**
	 * Returns the _inclination
	 * 
	 * @return _inclination the _inclination
	 */
	public synchronized double get_inclination() {
		return _inclination;
	}

	/**
	 * Sets the _inclination
	 * 
	 * @param _inclination
	 *            the _inclination to set
	 */
	public synchronized void set_inclination(double _inclination) {
		this._inclination = _inclination;
	}

	/**
	 * Returns the _raan
	 * 
	 * @return _raan the _raan
	 */
	public synchronized double get_raan() {
		return _raan;
	}

	/**
	 * Sets the _raan
	 * 
	 * @param _raan
	 *            the _raan to set
	 */
	public synchronized void set_raan(double _raan) {
		this._raan = _raan;
	}

	/**
	 * Returns the _eccentricity
	 * 
	 * @return _eccentricity the _eccentricity
	 */
	public synchronized double get_eccentricity() {
		return _eccentricity;
	}

	/**
	 * Sets the _eccentricity
	 * 
	 * @param _eccentricity
	 *            the _eccentricity to set
	 */
	public synchronized void set_eccentricity(double _eccentricity) {
		this._eccentricity = _eccentricity;
	}

	/**
	 * Returns the _argOfPerigee
	 * 
	 * @return _argOfPerigee the _argOfPerigee
	 */
	public synchronized double get_argOfPerigee() {
		return _argOfPerigee;
	}

	/**
	 * Sets the _argOfPerigee
	 * 
	 * @param _argOfPerigee
	 *            the _argOfPerigee to set
	 */
	public synchronized void set_argOfPerigee(double _argOfPerigee) {
		this._argOfPerigee = _argOfPerigee;
	}

	/**
	 * Returns the _meanAnomaly
	 * 
	 * @return _meanAnomaly the _meanAnomaly
	 */
	public synchronized double get_meanAnomaly() {
		return _meanAnomaly;
	}

	/**
	 * Sets the _meanAnomaly
	 * 
	 * @param _meanAnomaly
	 *            the _meanAnomaly to set
	 */
	public synchronized void set_meanAnomaly(double _meanAnomaly) {
		this._meanAnomaly = _meanAnomaly;
	}

	/**
	 * Returns the _meanMotion
	 * 
	 * @return _meanMotion the _meanMotion
	 */
	public synchronized double get_meanMotion() {
		return _meanMotion;
	}

	/**
	 * Sets the _meanMotion
	 * 
	 * @param _meanMotion
	 *            the _meanMotion to set
	 */
	public synchronized void set_meanMotion(double _meanMotion) {
		this._meanMotion = _meanMotion;
	}
}

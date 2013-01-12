/**
 * 
 */
package com.smmsp.time;

/**
 * This interface represents a series of conversions to all other 
 * formats for time instants.
 * 
 * @author sean
 *
 */
public interface TimeInstant {

	public GPSTime toGPSTime();
	
	public UnixTime toUnixTime();
}

/**
 * 
 */
package com.smmsp.time;

/**
 * @author sean
 *
 */
public class TimeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6310798823689428777L;

	/**
	 * @param arg0
	 */
	public TimeException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TimeException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}

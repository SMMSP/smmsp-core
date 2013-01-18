/**
 * 
 */
package com.smmsp.core.utils;

/**
 * @author sean
 *
 */
public abstract class ArrayUtils {

	public static int[] combine(final int[]... arrays){
		int[] combined = new int[0];
		for(int[] arr : arrays){
			combined = combine(combined, arr);
		}
		return combined;
	}
	
	public static int[] combine(final int[] first, final int[] second){
		final int[] combined = new int[first.length + second.length];
		int position = 0;
		for(int i = 0; i < first.length; ++i){
			combined[position++] = first[i];
		}
		for(int i = 0; i < second.length; ++i){
			combined[position++] = second[i];
		}
		return combined;
	}
	
	public static byte[] combine(final byte[]... arrays){
		byte[] combined = new byte[0];
		for(byte[] arr : arrays){
			combined = combine(combined, arr);
		}
		return combined;
	}
	
	public static byte[] combine(final byte[] first, final byte[] second){
		final byte[] combined = new byte[first.length + second.length];
		int position = 0;
		for(int i = 0; i < first.length; ++i){
			combined[position++] = first[i];
		}
		for(int i = 0; i < second.length; ++i){
			combined[position++] = second[i];
		}
		return combined;
	}
	
	
}

/**
 * 
 */
package com.smmsp.tests.utils;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.smmsp.core.utils.ArrayUtils;

/**
 * @author sean
 *
 */
public class ArrayUtilsTest {

	/**
	 * Test method for {@link com.smmsp.core.utils.ArrayUtils#combine(T[][])}.
	 */
	@Test
	public final void testCombine() {
		final int[] first = new int[]{0,2,4,6,8};
		final int[] second = new int[]{1,3,5,7,9};
		
		final int[] expected = new int[]{0,2,4,6,8,1,3,5,7,9};
		
		final int[] result = ArrayUtils.combine(first, second);
		assertTrue(Arrays.equals(result, expected));
	}
	
	@Test
	public final void testCombine1(){
		final int[] first = new int[]{1};
		final int[] second = new int[]{2};
		final int[] third = new int[]{3};
		
		final int[] expected = new int[]{1,2,3};
		
		final int[] result = ArrayUtils.combine(first, second, third);
		assertTrue(Arrays.equals(result,expected));
	}
	
	@Test 
	public final void testCombine2(){
		final byte[] first = new byte[]{0x0, 0x2};
		final byte[] second = new byte[]{0x1, 0x3};
		
		final byte[] expected = new byte[]{0x00, 0x02, 0x01, 0x03};
		
		final byte[] result = ArrayUtils.combine(first, second);
		
		assertTrue(Arrays.equals(result, expected));
	}
	
	@Test
	public final void testCombine3(){
		final byte[] first = new byte[]{0x0};
		final byte[] second = new byte[]{0x01};
		final byte[] third = new byte[]{0x02};
		
		final byte[] expected = new byte[]{0x00, 0x01, 0x02};
		
		final byte[] result = ArrayUtils.combine(first, second, third);
		
		assertTrue(Arrays.equals(result,expected));
	}

}

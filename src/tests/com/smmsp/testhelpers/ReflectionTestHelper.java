/**
 * 
 */
package com.smmsp.testhelpers;

import java.lang.reflect.Field;

/**
 * @author sean
 *
 */
public abstract class ReflectionTestHelper {

	/**
	 * Returns the value of the variable 'variable' within the object
	 * specified by 'instance' of the type 'clazz'.
	 * 
	 * @param clazz The enclosing class
	 * @param instance The instance of clazz
	 * @param variable The variable within instance.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, R> T GetPrivateVariable(
			Class<R> clazz, R instance, String variable){
		T obj = null;
		try {
			Field f = clazz.getField(variable);
			
			boolean accessible = f.isAccessible();
			f.setAccessible(true);
			obj = (T) f.get(instance);
			f.setAccessible(accessible);
			
			
		} catch (IllegalArgumentException | 
				IllegalAccessException | 
				SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}

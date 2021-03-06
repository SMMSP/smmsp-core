/**
 * 
 */
package com.smmsp.testhelpers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	
	@SuppressWarnings("unchecked")
	public static <T, R> T GetPrivateStaticVariable(
			Class<R> clazz, String variable){
		T obj = null;
		try {
			Field f = clazz.getDeclaredField(variable);
			boolean accessible = f.isAccessible();
			f.setAccessible(true);
			obj = (T) f.get(null);
			f.setAccessible(accessible);
			
		} catch ( NoSuchFieldException e){
			e.printStackTrace();
		} catch (SecurityException |
				IllegalArgumentException |
				IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public static <T, R> T CallPrivateMethod(
			R instance, 
			String method, 
			Object... args){
		Class<?>[] classes = new Class<?>[args.length];
		
		int i = 0;
		for(Object o : args){
			classes[i++] = o.getClass();
		}
		
		try {
			Method m = instance.getClass().getMethod(method, classes);
			
			boolean accessible = m.isAccessible();
			m.setAccessible(true);
			T obj = (T) m.invoke(instance, args);
			m.setAccessible(accessible);
			
			return obj;
		} catch (NoSuchMethodException e){
			e.printStackTrace();
		}catch(SecurityException  e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

package com.sinmn.core.utils.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * @Description 复制属性专用
 * @author xhz
 * @date 2017年4月24日 下午2:24:22
 */
public class BeanUtil {

	/**
	 * @Description 单个复制
	 * @author xhz
	 * @date 2017年4月24日 下午2:24:38
	 * @param source
	 * @param clazz
	 * @return
	 * @lastModifier
	 */
	public static <T> T copy(Object source,Class<T> clazz)
	{
		if(source == null){
			return null;
		}
		try {
			T target = clazz.newInstance();
			copy(source,target);
			return target;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
		
	}
	
	/**
	 * @Description 获取所有的成员变量，包括父类
	 * @author xhz
	 * @date 2017年4月24日 下午2:24:49
	 * @param clazz
	 * @return
	 * @lastModifier
	 */
	public static List<Field> getAllField(Class<?> clazz){
		
		List<Field> liField = new ArrayList<Field>();
		while(true){
			Field[] fields = clazz.getDeclaredFields();
			for(Field field :fields){
				liField.add(field);
			}
			clazz = clazz.getSuperclass();
			if(clazz == null){
				break;
			}
		}
		return liField;
	}
	
	/**
	 * @Description 获取属性，包括父类
	 * @author xhz
	 * @date 2017年4月24日 下午2:25:36
	 * @param clazz
	 * @param name
	 * @return
	 * @lastModifier
	 */
	public static Field getField(Class<?> clazz,String name){
		Field field = null;
		while(true){
			try {
				field = clazz.getDeclaredField(name);
				break;
			} catch (Exception e) {
			}
			clazz = clazz.getSuperclass();
			if(clazz == null){
				break;
			}
		}
		
		return field;
	}
	
	/**
	 * @Description 复制所有属性
	 * @author xhz
	 * @date 2017年4月24日 下午2:24:06
	 * @param source
	 * @param target
	 * @lastModifier
	 */
	public static void copy(Object source,Object target){
		if(source == null || target == null) return; 
		
		Class<?> clazz = target.getClass();
		Class<?> sourceClazz = source.getClass();
		
		List<Field> liFiled = getAllField(clazz);
		for(Field field : liFiled){
			Field srouceField = getField(sourceClazz,field.getName());
			if(srouceField == null){
				continue;
			}
			field.setAccessible(true);
			srouceField.setAccessible(true);
			try {
				field.set(target, srouceField.get(source));
			} catch (Exception e) {
			} 
		}
	}
	
	/**
	 * @Description 批量复制属性
	 * @author xhz
	 * @date 2017年4月24日 下午2:26:06
	 * @param source
	 * @param clazz
	 * @return
	 * @lastModifier
	 */
	public static <T> List<T> copy(List source,Class<T> clazz){
		List<T> result = new ArrayList<T>();
		if(source == null || source.isEmpty()){
			return result;
		}
		for(Object o : source){
			T e = copy(o,clazz);
			result.add(e);
		}
		
		return result;
	}
	
	public static void copyFromExits(Object source, Object target) {
		if(source == null || target == null) return;

		Class<?> clazz = target.getClass();
		Class<?> sourceClazz = source.getClass();

		List<Field> liFiled = getAllField(clazz);
		for(Field field : liFiled){
			Field srouceField = getField(sourceClazz,field.getName());
			if(srouceField == null){
				continue;
			}
			field.setAccessible(true);
			srouceField.setAccessible(true);
			try {
				if(null != srouceField.get(source)) {
					field.set(target, srouceField.get(source));
				}
			} catch (Exception e) {
			}
		}
	}
	
	public static void initCreate(Object obj,long user_id){
		initModify(obj,user_id);
		Class<?> clazz = obj.getClass();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("creator",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, user_id);
			}
		} catch (Exception e) {}
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor("createTime",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, new Date());
			}
		} catch (Exception e) {}	
		
	}
	
	
	
	public static void initModify(Object obj,long user_id){
		Class<?> clazz = obj.getClass();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("modifier",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, user_id);
			}
		} catch (Exception e) {}
		
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor("modifyTime",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, new Date());
			}
		} catch (Exception e) {}
	}
	
	public static void initModify(Object obj,String name){
		Class<?> clazz = obj.getClass();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("modifyName",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, name);
			}
		} catch (Exception e) {}
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor("modifyTime",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, new Date());
			}
		} catch (Exception e) {}	
		
	}
	
	public static void initCreate(Object obj,String name){
		initModify(obj,name);
		Class<?> clazz = obj.getClass();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("createName",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, name);
			}
		} catch (Exception e) {}
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor("createTime",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, new Date());
			}
		} catch (Exception e) {}	
		
	}
}

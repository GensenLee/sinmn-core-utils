package com.sinmn.core.utils.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description 和map相关的工具类
 * @author zhangxh
 * @date 2016-8-11 下午9:04:12
 */
public class MapUtil
{
    /**
     * @Description 将list转换为map
     * @author zhangxh
     * @date 2016-8-11 下午9:28:05
     * @param list
     * @param field
     * @return
     * @lastModifier
     */
    public static <T,T2> Map<T2,T> toMap(List<T> list,String field,Class<T2> cla)
    {
        Map<T2,T> result = new HashMap<T2,T>();
        if(list == null || list.isEmpty()){
            return result;
        }
        for(T o : list){
        	if( o == null) continue;
            Class<?> clazz = o.getClass();
            try{
            	PropertyDescriptor pd = null;
            	try{
            		pd = new PropertyDescriptor(field,clazz);
            	}catch(Exception e){
            		pd = new PropertyDescriptor(StringUtil.toLHCase(field),clazz);
            	}
				Method method = pd.getReadMethod();
				
                Object value = method.invoke(o);
                
                if(cla.equals(String.class)){
                	if(value != null){
                		result.put((T2)value.toString(), o);
	                }
                }else{
	                Method valueOf = cla.getMethod("valueOf", String.class);
	                if(value == null){
	                	result.put((T2)valueOf.invoke(null, "0"), o);
	                }else{
	                	result.put((T2)valueOf.invoke(null, value.toString()), o);
	                }
                }
                
            }catch(Exception e){
                
            }
        }
        return result;
    }
    
    
    /**
     * @Description 将list转换为map
     * @author zhangxh
     * @date 2016-8-11 下午9:28:05
     * @param list
     * @param field
     * @return
     * @lastModifier
     */
    public static <T> Map<Long,T> toMap(List<T> list,String field)
    {
        return toMap(list,field,Long.class);
    }
    
    public static <T> Map<Integer,T> toMapInteger(List<T> list,String field)
    {
        return toMap(list,field,Integer.class);
    }
    
    /**
     * @Description 将list转换为map
     * @author zhangxh
     * @date 2016-8-11 下午9:28:05
     * @param list
     * @param field
     * @return
     * @lastModifier
     */
    public static <T> Map<Long,T> toMapLong(List<T> list,String field)
    {
        return toMap(list,field,Long.class);
    }
    
    /**
     * 
     * @Description 将list转换为map
     * @author chensongming
     * @date 2017年8月3日 下午4:39:34
     * @param list
     * @param field
     * @return
     * @lastModifier
     */
    public static <T> Map<String,T> toMapString(List<T> list,String field)
    {
        return toMap(list,field,String.class);
    }
    
    /**
     * @Description 将List 分组
     * @author xhz
     * @date 2016-9-1 下午2:42:51
     * @param list
     * @param field
     * @return
     * @lastModifier
     */
    public static <T> Map<Integer,List<T>> toMapList(List<T> list,String field)
    {
        Map<Integer,List<T>> result = new HashMap<Integer,List<T>>();
        if(list == null || list.isEmpty()){
            return result;
        }
        for(T o : list){
            Class<?> clazz = o.getClass();
            try{
            	PropertyDescriptor pd = null;
            	try{
            		pd = new PropertyDescriptor(field,clazz);
            	}catch(Exception e){
            		pd = new PropertyDescriptor(StringUtil.toLHCase(field),clazz);
            	}
				Method method = pd.getReadMethod();
                Object value = method.invoke(o);
                List<T> listValue = result.get(value);
                if(listValue == null){
                    listValue = new ArrayList<T>();
                    result.put(IntUtil.toInt(value), listValue);
                }
                listValue.add(o);
            }catch(Exception e){
                
            }
        }
        return result;
    }
    
    /**
     * @Description 将list分组
     * @author xhz
     * @date 2016-9-1 下午2:43:08
     * @param list
     * @param field
     * @return
     * @lastModifier
     */
    public static <T> Map<String,List<T>> toMapListByString(List<T> list,String field)
    {
        Map<String,List<T>> result = new HashMap<String,List<T>>();
        if(list == null || list.isEmpty()){
            return result;
        }
        for(T o : list){
            Class<?> clazz = o.getClass();
            try{
            	PropertyDescriptor pd = null;
            	try{
            		pd = new PropertyDescriptor(field,clazz);
            	}catch(Exception e){
            		pd = new PropertyDescriptor(StringUtil.toLHCase(field),clazz);
            	}
				Method method = pd.getReadMethod();
                Object value = method.invoke(o);
                List<T> listValue = result.get(value);
                if(listValue == null){
                    listValue = new ArrayList<T>();
                    result.put(value==null?"":value.toString(), listValue);
                }
                listValue.add(o);
            }catch(Exception e){
                
            }
        }
        return result;
    }
    
    /**
     * @Description 
     * @author xhz
     * @date 2016-9-1 下午3:08:25
     * @param map
     * @param key
     * @return
     * @lastModifier
     */
    public static <T1,T2> List<T2> getList(Map<T1,List<T2>> map,T1 key)
    {
        List<T2> result = map.get(key);
        if(result == null){
            result = new ArrayList<T2>();
        }
        return result;
    }
    
    /**
     * @Description 
     * @author aikq
     * @date 2017年6月6日 上午9:32:41
     * @param list
     * @param field
     * @return
     * @lastModifier
     */
    public static <T> Map<Long,List<T>> toMapListByLong(List<T> list,String field)
    {
        Map<Long,List<T>> result = new HashMap<Long,List<T>>();
        if(list == null || list.isEmpty()){
            return result;
        }
        for(T o : list){
            Class<?> clazz = o.getClass();
            try{
            	PropertyDescriptor pd = null;
            	try{
            		pd = new PropertyDescriptor(field,clazz);
            	}catch(Exception e){
            		pd = new PropertyDescriptor(StringUtil.toLHCase(field),clazz);
            	}
				Method method = pd.getReadMethod();
                Object value = method.invoke(o);
                List<T> listValue = result.get(value);
                if(listValue == null){
                    listValue = new ArrayList<T>();
                    result.put(LongUtil.toLong(value), listValue);
                }
                listValue.add(o);
            }catch(Exception e){
                
            }
        }
        return result;
    }
    
    public static Map<String,Object> toMapResult(Object source,String ...fields){
    	return toMapResult(source,null,fields);
    }
    public static Map<String,Object> toMapResult(Object source,String[] arrFields,String ...fields){
    	if(source == null){
    		return null;
    	}
    	Map<String,Object> resultTmp = FastJsonUtils.getBean(FastJsonUtils.toJsonString(source), Map.class);
    	Map<String,Object> result = new HashMap<String,Object>();
    	if(arrFields != null){
    		for(String key : arrFields){
        		result.put(key, resultTmp.get(key));
        	}
    	}
    	for(String key : fields){
    		result.put(key, resultTmp.get(key));
    	}
    	return result;
    	
    }
    
    public static Map<String,Object> toMapResultExclude(Object source,String ...fields){
    	return toMapResultExclude(source,null,fields);
    }
    public static Map<String,Object> toMapResultExclude(Object source,String[] arrFields,String ...fields){
    	if(source == null || source instanceof List){
    		return null;
    	}
    	Map<String,Object> result = FastJsonUtils.getBean(FastJsonUtils.toJsonString(source), Map.class);
    	if(arrFields != null){
    		for(String key : arrFields){
        		result.remove(key);
        	}
    	}
    	for(String key : fields){
    		result.remove(key);
    	}
    	return result;
    	
    }
    
    public static List<Map<String,Object>> toMapListResult(Object source,String ...fields){
    	return toMapListResult(source,null,fields);
    }
    public static List<Map<String,Object>> toMapListResult(Object source,String[] arrFields,String ...fields){
    	if(source == null){
    		return null;
    	}
    	if(!(source instanceof List)){
    		return null;
    	}
    	List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
    	for(Object o : (List)source){
    		result.add(toMapResult(o,arrFields,fields));
    	}
    	return result;
    	
    }
    
    public static List<Map<String,Object>> toMapListResultExclude(Object source,String ...fields){
    	return toMapListResultExclude(source, null, fields);
    }
    
    public static List<Map<String,Object>> toMapListResultExclude(Object source,String[] arrFields,String ...fields){
    	if(source == null){
    		return null;
    	}
    	if(!(source instanceof List)){
    		return null;
    	}
    	List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
    	for(Object o : (List)source){
    		result.add(toMapResultExclude(o,arrFields,fields));
    	}
    	return result;
    	
    }
    

	/**
	 * 把参数转为map
	 * @param obj
	 * @return
	 * @throws Throwable
	 */
	public static Map<String, Object> changeToMap(Object obj) {
		@SuppressWarnings("rawtypes")

		Class cls = obj.getClass();

		List<Field> fields = BeanUtil.getAllField(cls);

		Map<String, Object> params = new TreeMap<String, Object>();
		// 将请求的Bean类值转换为Map数据类型
		for (Field fieldElement : fields) {
			// 获取字段名称
			String fieldName = fieldElement.getName();
			if ("".equals(fieldName) || "serialVersionUID".equals(fieldName))
				continue;
			fieldElement.setAccessible(true);
			// 取出对应字段的值
			Object objValue=null;
			try {
				objValue = fieldElement.get(obj);
			} catch (Exception e) {
			   return params;
			}
			// 对Value进行赋值
			Object value = objValue != null ? objValue : "";

			if ("".equals(value))
				continue;
			params.put(fieldName, value);
		}

		return params;
	}
}

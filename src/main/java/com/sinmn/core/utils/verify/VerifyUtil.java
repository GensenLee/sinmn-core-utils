package com.sinmn.core.utils.verify;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.core.annotation.AnnotationUtils;

import com.sinmn.core.utils.util.IntUtil;
import com.sinmn.core.utils.util.StringUtil;

/**
 * @author xhz
 * 验证类
 */
public class VerifyUtil {
	
    /**
     * @Description 判断是否有空值
     * @author xhz
     * @date 2016-9-2 下午5:56:01
     * @param arr
     * @throws VerifyException
     * @lastModifier
     */
    public static void verify(Object[] arr)
            throws VerifyException
    {
        for(Object o : arr){
            if(o == null){
                throw new VerifyException("null");
            }else if(o instanceof String){
                if(o.toString() == null || o.toString().equals("")){
                    throw new VerifyException("null");
                }
            }else if(o instanceof Integer){
                if(IntUtil.isZero(o)){
                    throw new VerifyException("null");
                }
            }
        }
    }
    
    /**
     * @Description 判断是否有空值
     * @author xhz
     * @date 2016-9-2 下午5:56:20
     * @param vo
     * @throws VerifyException
     * @lastModifier
     */
    public static void verify(Object vo)
    		throws VerifyException{
    	verify(vo,new String[]{},true);
    }
    
    /**
     * @Description 验证值
     * @author xhz
     * @date 2017年5月23日 上午10:23:57
     * @param vo
     * @param fields
     * @throws VerifyException
     * @lastModifier
     */
    public static void verify(Object vo,Field[] fields)
    		throws VerifyException{
    	for(Field field : fields){
            try {
            	VerifyField verifyField = AnnotationUtils.findAnnotation(field,VerifyField.class);
            	if(verifyField != null && verifyField.ignore() == true){
            		continue;
            	}
            	String msg = verifyField == null?field.getName():verifyField.value();
                field.setAccessible(true);
                Object value = field.get(vo);
                if(value == null){
                    throw new VerifyException(field.getName(),msg+"不能为空");
                }else if(value instanceof String){
                	if(value.toString().equals("")){
                        throw new VerifyException(field.getName(),msg+"不能为空");
                    }
                	if(verifyField != null && !verifyField.regex().equals("")){
                		if(!value.toString().matches(verifyField.regex())){
                			throw new VerifyException(field.getName(),msg+"验证不通过");
                		}
                	}
                } else if(value instanceof Integer){
                    if(Integer.valueOf(value.toString()) == 0 && (verifyField != null && verifyField.notZero())){
                        throw new VerifyException(field.getName(),msg+"不能为空或0");
                    }
                }else if(value instanceof Long){
                    if(Long.valueOf(value.toString()) == 0 && (verifyField != null && verifyField.notZero())){
                        throw new VerifyException(field.getName(),msg+"不能为空或0");
                    }
                }else if(value instanceof Byte){
                    if(Byte.valueOf(value.toString()) == 0 && (verifyField != null && verifyField.notZero())){
                        throw new VerifyException(field.getName(),msg+"不能为空或0");
                    }
                }else if(value instanceof BigDecimal){
                    if(BigDecimal.valueOf(Double.valueOf(value.toString())).equals(BigDecimal.ZERO) && (verifyField != null && verifyField.notZero())){
                        throw new VerifyException(field.getName(),msg+"不能为空或0");
                    }
                }
                else if(value instanceof List){
                    if(((List)value).isEmpty()){
                    	throw new VerifyException(field.getName(),msg+"不能为空");
                    }
                }else if(value instanceof Map){
                    if(((Map)value).isEmpty()){
                    	throw new VerifyException(field.getName(),msg+"不能为空");
                    }
                }else{
                	if(value.toString().equals("")){
                        throw new VerifyException(field.getName(),msg+"不能为空");
                    }
                }
            } catch(VerifyException ve){
            	throw ve;
            }catch (Exception e) {
                throw new VerifyException(field.getName());
            } 
        }
    }
    
    public static void verify(Object vo,String fields)
    		throws VerifyException
    {
    	verify(vo,fields.split(","));
    }
    
    private static Field getField(String name,Class<?> clazz)
    		throws NoSuchFieldException
    {
    	while(clazz != null && !clazz.getName().equals("java.lang.Object")){
    		try {
    			return clazz.getDeclaredField(name);
			} catch (Exception e) {
				try{
					return clazz.getDeclaredField(StringUtil.toLHCase(name));
				}catch(Exception ee){
					
				}
			}
			clazz = clazz.getSuperclass();
		}
    	throw new NoSuchFieldException();
    }
    
    /**
	 * @Description 判断是否有空值
	 * @author xhz
	 * @date 2016-9-2 下午5:56:40
	 * @param vo
	 * @param fields
	 * @throws VerifyException
	 * @lastModifier
	 */
	public static void verify(Object vo,String... fields)
		throws VerifyException
	{
		if(vo == null){
			throw new VerifyException("VO","输入值为null");
		}
		Class<?> clazz = vo.getClass(); 
		List<Field> liField = new ArrayList<Field>();
		for(String field : fields){
			try {
				liField.add(getField(field,clazz));
			} catch (Exception e) {
				throw new VerifyException(field,e.getMessage());
			} 
		}
		Field[] arrField = new Field[liField.size()];
		arrField = liField.toArray(arrField);
    	verify(vo,arrField);
	}
    
    /**
     * @Description 判断是否有空值
     * @author xhz
     * @date 2016-9-2 下午5:56:20
     * @param vo
     * @throws VerifyException
     * @lastModifier
     */
    public static void verify(Object vo,String[] ignoreFields,boolean ignore)
            throws VerifyException
    {
        if(vo == null){
            throw new VerifyException("VO","输入值为null");
        }
        List<String> liIgnoreField = Arrays.asList(ignoreFields);
        Class<?> clazz = vo.getClass(); 
        Field[] fields = clazz.getDeclaredFields();
        List<Field> liField = new ArrayList<Field>();
        for(Field field : fields){
            try {
            	if(liIgnoreField.indexOf(field.getName()) != -1){
            		continue;
            	}
            	liField.add(field);
            } catch (Exception e) {
                throw new VerifyException(field.getName(),e.getMessage());
            } 
        }
        fields = new Field[liField.size()];
        fields = liField.toArray(fields);
    	verify(vo,fields);
        
    }
}

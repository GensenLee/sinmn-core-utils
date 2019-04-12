package com.sinmn.core.utils.apiEnhanced.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

import com.sinmn.core.utils.apiEnhanced.enums.ApiEnhancedType;

/**
 * @author xhz
 * 接口增强注解类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiEnhanced {
	
	/**
	 * 过期时间，默认三分钟
	 */
	@AliasFor("expire")
	int value() default 180;
	
	/**
	 * 过期时间，默认三分钟
	 */
	@AliasFor("value")
	int expire() default 180;
	
	/**
	 * 注解参数uuid,默认为空
	 */
	String uuid() default "";
	
	/**
	 * 是否单例，默认否
	 */
	boolean single() default false;
	
	/**
	 * 调用后移除幂等UUID 单例模式下，这个值一般会设置为true
	 */
	boolean removeAfter() default false;
	
	/**
	 * 接口增强的方式,默认为接口缓存数据
	 */
	ApiEnhancedType type() default ApiEnhancedType.CACHEABLE; 
	
	
}

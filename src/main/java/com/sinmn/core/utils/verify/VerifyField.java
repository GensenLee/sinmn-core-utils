package com.sinmn.core.utils.verify;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface VerifyField {

	public static final String EMAIL = "^.+?@.+\\..+$";
	
	/**
	 * @Description 是否忽略
	 * @author xhz
	 * @date 2017年5月23日 上午9:27:30
	 * @return
	 * @lastModifier
	 */
	public boolean ignore() default false;
	
	/**
	 * @Description 出错后的字段说明
	 * @author xhz
	 * @date 2017年5月23日 上午9:27:42
	 * @return
	 * @lastModifier
	 */
	public String value() default "";
	
	
	/**
	 * @Description 正则表达式
	 * @author xhz
	 * @date 2017年5月23日 上午10:25:58
	 * @return
	 * @lastModifier
	 */
	public String regex() default "";
	
	/**
	 * @Description 不能为0
	 * @author xhz
	 * @date 2018年9月19日 下午9:24:13
	 * @return
	 * @lastModifier
	 */
	public boolean notZero() default false;
}

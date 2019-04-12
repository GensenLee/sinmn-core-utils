package com.sinmn.core.utils.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Spring context util
 * 获得 Spring context 的 context
 *
 */
public class SpringContextUtil {

	private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);
	
	private static ApplicationContext context = null;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		SpringContextUtil.context = context;
		if(logger.isDebugEnabled()){
			String[] names = context.getBeanDefinitionNames();
			for(String name : names){
				logger.debug("****************"+name);
			}
		}
	}

	public static Object getBean(String beanId) {
		return context.getBean(beanId);
	}														
	
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}
}

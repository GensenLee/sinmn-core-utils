package com.sinmn.core.utils.apiEnhanced.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.sinmn.core.utils.redis.configuration.RedisConfiguration;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({ApiEnhancedConfiguration.class,RedisConfiguration.class})
public @interface EnableApiEnhanced {

}

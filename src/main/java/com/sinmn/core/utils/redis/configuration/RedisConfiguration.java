package com.sinmn.core.utils.redis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sinmn.core.utils.redis.aspect.RedisAspect;

@Configuration
public class RedisConfiguration {
   
	@Bean
	public RedisAspect redisAspect(){
		return new RedisAspect();
	}
}

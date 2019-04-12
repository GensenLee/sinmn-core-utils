package com.sinmn.core.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class AspectRedisDao {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ThreadLocal<Jedis> jedisHolder = new ThreadLocal<Jedis>();
	
	public Jedis getJedis(){
		return jedisHolder.get();
	}
	
	public void setJedis(Jedis jedis){
		jedisHolder.set(jedis);
	}
}

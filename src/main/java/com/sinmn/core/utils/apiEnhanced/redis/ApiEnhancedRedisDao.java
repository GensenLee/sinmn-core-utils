package com.sinmn.core.utils.apiEnhanced.redis;

import com.sinmn.core.utils.redis.AspectRedisDao;
import com.sinmn.core.utils.redis.annotation.Redis;
import com.sinmn.core.utils.util.StringUtil;

import lombok.extern.log4j.Log4j;
import redis.clients.jedis.Jedis;

@Log4j
public class ApiEnhancedRedisDao extends AspectRedisDao{
	
	/**
	 * 采用key->Strings的方式存储数据
	 */
	/** %s为uuid*/
	private static final String API_ENHANCED_UUID_KEY = "API_ENHANCED_UUID_KEY-%s";
	
	
	@Redis
	public long setAccessTimes(String uuid, Integer expireTime) throws Exception {

		if(StringUtil.isEmpty(uuid)){
			throw new Exception("传入的uuid为空");
		}
		Jedis jedis = getJedis();
		String key   = String.format(API_ENHANCED_UUID_KEY, uuid);
		
		long accessTimes = jedis.incr(key);
		//设置过期时间
		if(accessTimes == 1){
			jedis.expire(key, expireTime);
		}
		return accessTimes;
	}
	
	/**
	 * 删除UUID
	 * @param uuid
	 */
	public void deleteUUID(String uuid){
		
		if(StringUtil.isEmpty(uuid)){
			return;
		}
		Jedis jedis =  getJedis();
		String key = String.format(API_ENHANCED_UUID_KEY, uuid);
		jedis.del(key);
	}
}

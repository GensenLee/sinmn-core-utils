package com.sinmn.core.utils.redis.aspect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinmn.core.utils.redis.annotation.Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

@Aspect
public class RedisAspect {

	private static Logger logger = LoggerFactory.getLogger(RedisAspect.class);

	@Resource(name = "jedisPool")
	private JedisPool jedisPool;

	protected boolean handleJedisException(JedisException jedisException) {
	    if (jedisException instanceof JedisConnectionException) {
	        logger.error("[RedisAspect.handleJedisException] Redis connection  lost.", jedisException);
	    } else if (jedisException instanceof JedisDataException) {
	        if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
	            logger.error("[RedisAspect.handleJedisException] Redis connection are read-only slave.", jedisException);
	        } else {
	            // dataException, isBroken=false
	            return false;
	        }
	    } else {
	        logger.error("[RedisAspect.handleJedisException] Jedis exception happen.", jedisException);
	    }
	    return true;
	}
	
	protected void closeResource(Jedis jedis, boolean conectionBroken) {
		if(jedis == null){
			return;
		}
//		if (conectionBroken) {
//            jedisPool.returnBrokenResource(jedis);
//        } else {
//            jedisPool.returnResource(jedis);
//        }
		jedis.close();
	}
	
	//定义环绕通知
	@Around("execution( * *(..)) and @annotation(redis)")
	public Object around(ProceedingJoinPoint  pjp, Redis redis) throws Throwable{
			
		if(logger.isDebugEnabled()){
			logger.debug("[RedisAspect.around] [{} {}() start] redis aop启动 ......",pjp.getTarget().getClass().getName(),pjp.getSignature().getName());
		}
		
		boolean broken = false;
		Jedis jedis = null;
		
		PropertyDescriptor pd = new PropertyDescriptor("jedis", pjp.getTarget().getClass());
        Method setMethod = pd.getWriteMethod();
        if(setMethod == null){
        	return null;
        }
        
		try{
			jedis = jedisPool.getResource();

	        setMethod.invoke(pjp.getTarget(), jedis);
	        
			Object[] args = pjp.getArgs();
			Object result = pjp.proceed(args);
			
			if(logger.isDebugEnabled()){
				logger.debug("[RedisAspect.around] [{} {}() end] redis 执行完毕......",pjp.getTarget().getClass().getName(),pjp.getSignature().getName());
			}
			return result;
		}catch(JedisException e){
			broken = handleJedisException(e);
		}catch(Exception e){
			logger.error("[RedisAspect exception] 出错啦!!!",e);
		}finally{
			closeResource(jedis, broken);
			//设置一个空值
			setMethod.invoke(pjp.getTarget(),new Object[]{null});
		}
		return null;
		
	}
}

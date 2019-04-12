package com.sinmn.core.utils.apiEnhanced.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sinmn.core.utils.apiEnhanced.annotation.ApiEnhanced;
import com.sinmn.core.utils.apiEnhanced.enums.ApiEnhancedType;
import com.sinmn.core.utils.apiEnhanced.vo.ApiEnhancedHeaderVO;
import com.sinmn.core.utils.util.FastJsonUtils;
import com.sinmn.core.utils.util.StringUtil;

/**
 * api增强器拦截器
 * @author xhz
 *
 */
@Aspect
@Component
@Order(999)
public class ApiEnhancedAspect {
	
	private static Logger logger = LoggerFactory.getLogger(ApiEnhancedAspect.class);
	
//	@Autowired
//	private IdempotentRedisDao idempotentRedisDao;
	
	/**
	 * 缓存
	 * @param pjp
	 * @param apiEnhanced
	 * @param apiEnhancedHeaderVO
	 * @return
	 */
	private Object cacheable(ProceedingJoinPoint  pjp, ApiEnhanced apiEnhanced,ApiEnhancedHeaderVO apiEnhancedHeaderVO) throws Throwable{
		logger.info("[around.cacheable] 接口增强前置拦截aop结束......");
		 //先拿到url参数，组装成map
		return pjp.proceed(pjp.getArgs());
	}
	
	private Object idempotent(ProceedingJoinPoint  pjp, ApiEnhanced apiEnhanced,ApiEnhancedHeaderVO apiEnhancedHeaderVO) throws Throwable{
		 logger.info("[around.idempotent] 接口增强前置拦截aop结束......");
		return pjp.proceed(pjp.getArgs());
	}
	
	//定义环绕通知
	@Around("execution( * *(..)) and @annotation(apiEnhanced)")
	public Object around(ProceedingJoinPoint  pjp, ApiEnhanced apiEnhanced) throws Throwable{
				
		logger.info("[around] 接口增强前置拦截aop启动......");
		
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String strApiEnhanced = request.getHeader("apiEnhanced");
        
        logger.info("[around] strApiEnhanced:{}",strApiEnhanced);
        ApiEnhancedHeaderVO apiEnhancedHeaderVO = null;
        if(StringUtil.isNotEmpty(strApiEnhanced)){
        	apiEnhancedHeaderVO = FastJsonUtils.getBean(strApiEnhanced, ApiEnhancedHeaderVO.class);
        }
        
        if(apiEnhancedHeaderVO == null){
        	//保持下面的逻辑统一
        	apiEnhancedHeaderVO = new ApiEnhancedHeaderVO();
        }
        
        if(apiEnhanced.type() != null && apiEnhanced.type().equals(ApiEnhancedType.CACHEABLE)){
        	return cacheable(pjp,apiEnhanced,apiEnhancedHeaderVO);
        }else if(apiEnhanced.type() != null && apiEnhanced.type().equals(ApiEnhancedType.IDEMPOTENT)){
        	return idempotent(pjp,apiEnhanced,apiEnhancedHeaderVO);
        }
        
        
        logger.info("[around] 接口增强前置拦截aop结束......");
		return pjp.proceed(pjp.getArgs());
	}
	
//	//异常通知
//	@AfterThrowing(pointcut = "execution( * *(..)) and @annotation(idempotent)",throwing = "exception")
//	public void afterThrowing(JoinPoint joinPoint, Idempotent idempotent, RuntimeException exception) throws IdempotentException,RuntimeException{
//		
//		logger.info("[IdempotentAspect.afterThrowing]幂等异常拦截aop启动......");
//		
//		Signature sig = joinPoint.getSignature();
//		MethodSignature msig = null;
//		if(!(sig instanceof MethodSignature)){
//			throw new IllegalArgumentException("该注解只能用于方法");
//		}
//		
//		msig = (MethodSignature) sig;
//		
//		String name = joinPoint.getTarget().getClass().getName()+"."+msig.getName();
//		
//		Object[] args = joinPoint.getArgs();
//
//		
//		//是否允许多次调用
//		boolean multiCall = idempotent.multiCall();
//		
//		String uuid = idempotent.uuid();
//		
//		
//		Object targetBean = null;
//		
//		if(StringUtil.isEmpty(uuid)){
//			//允许多次调用，必须传递对象参数
//			if(args != null && args.length > 0){
//				for(Object argument : args){
//					if(argument instanceof BaseIdempotentVO){
//						targetBean = argument;
//						break;
//					}
//				}
//			}
//				
//			if(targetBean != null){
//				uuid = IdempotentUtil.encryptHashCode(targetBean);
//				
//				if(StringUtil.isEmpty(uuid)){
//					throw new IdempotentException("获取对象uuid出错");
//				}
//				//加上方法的名字
//				//uuid = uuid + "_"+name;
//			}
//		}
//		
//		if(StringUtil.isEmpty(uuid) && !multiCall){
//			uuid = name;
//		}
//		if(StringUtil.isEmpty(uuid)){
//			throw new IdempotentException("参数错误，请确认参数对象是否继承IdempotentBean");
//		}
//		
//		//拿到UUID  
//		logger.info("[IdempotentAspect.afterThrowing]IdempotentInterceptor.doAfterThrow {} uuid:{}", name, uuid);
//		
//		
//		//加上方法的签名
//		uuid = name+"_"+uuid;
//		logger.info("[IdempotentAspect.afterThrowing]IdempotentInterceptor.doAfterThrow {} uuid:{}", name, uuid);
//		
//		try{
//			if(!(exception instanceof IdempotentException)){
//				idempotentRedisDao.deleteUuid(uuid);
//			}
//		}catch(Exception e){
//			logger.error(e.getMessage(),e);
//			throw new IdempotentException(e);
//		}finally{
//			logger.info("[IdempotentAspect.afterThrowing]经过幂等拦截器类：" + name);
//		}
//		throw exception;
//	}
	
}

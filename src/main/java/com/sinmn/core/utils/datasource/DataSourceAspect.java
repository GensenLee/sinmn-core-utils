package com.sinmn.core.utils.datasource;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Aspect
@Order(-10)
public class DataSourceAspect {

	private static final Logger log = LoggerFactory.getLogger(DataSourceAspect.class);
	
	private static final ThreadLocal<Map<String,String>> contextHolder = new ThreadLocal<Map<String,String>>();
	
	@Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
		
		String oldDataSourceType = DataSourceContextHolder.getDataSourceType();
		Map<String,String> mapDataSourceType = contextHolder.get();
		if(mapDataSourceType == null){
			mapDataSourceType = new HashMap<String,String>();
			contextHolder.set(mapDataSourceType);
		}
		mapDataSourceType.put(point.getSignature().toString(), oldDataSourceType);
		
		String dataSourceType = dataSource.value();
		log.info("[DataSourceAspect.changeDataSource] 方法:{},选择数据源:{}",point.getSignature(),dataSourceType);
		DataSourceContextHolder.setDataSourceType(dataSourceType);
    }

    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
    	Map<String,String> mapDataSourceType = contextHolder.get();
    	String oldDataSourceType = null;
    	if(mapDataSourceType != null){
    		oldDataSourceType = mapDataSourceType.get(point.getSignature().toString());
    		mapDataSourceType.remove(point.getSignature().toString());
    	}
    	log.info("[DataSourceAspect.restoreDataSource] 方法:{},恢复数据源:{}",point.getSignature(),oldDataSourceType);
        DataSourceContextHolder.setDataSourceType(oldDataSourceType);
    }
}

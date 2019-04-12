package com.sinmn.core.utils.sql.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinmn.core.utils.sql.vo.WhereVO;
import com.sinmn.core.utils.util.BeanUtil;
import com.sinmn.core.utils.util.FastJsonUtils;

public class SQLUtil {
	
	
	/**
	 * @Description 转换成SQL语句
	 * @author xhz
	 * @date 2017年5月26日 下午4:08:38
	 * @param table
	 * @param whereVO
	 * @return
	 * @lastModifier
	 */
	public static String toSQL(WhereVO whereVO){
		if(whereVO == null){
			return "";
		}
		return whereVO.toString();
	}
	
	/**
	 * @Description 转换成SQL语句
	 * @author xhz
	 * @date 2017年5月26日 下午4:07:18
	 * @param listWhere
	 * @return
	 * @lastModifier
	 */
	public static String toSQL(List<WhereVO> listWhere){
		if(listWhere == null || listWhere.isEmpty()){
			return "";
		}
		WhereVO whereVO = new WhereVO();
		whereVO.setListWhere(listWhere);
		return toSQL(whereVO);
	}
	
	
	public static List<WhereVO> getList(List<WhereVO> listWhere,String table){
		List<WhereVO> result = new ArrayList<WhereVO>();
		if(listWhere == null) return result;
		for(WhereVO whereVO : listWhere){
			if(table.equals(whereVO.getTable())){
				result.add(whereVO);
			}
			List<WhereVO> r2 = getList(whereVO.getListWhere(),table);
			result.addAll(r2);
		}
		return result;
	}
	
	public static void replace(WhereVO source,List<WhereVO> target){
		if(target == null) return;
		for(WhereVO whereVO : target){
			if(whereVO.getField() != null && whereVO.getField().equals(source.getField())){
				BeanUtil.copy(source, whereVO);
			}
			replace(source,whereVO.getListWhere());
		}
	}
	
	public static void replace(List<WhereVO> source,List<WhereVO> target){
		for(WhereVO whereVO : source){
			replace(whereVO,target);
		}
	}
	
	/**
	 * @Description 通过table分裂
	 * @author xhz
	 * @date 2017年6月21日 上午11:53:58
	 * @return
	 * @lastModifier
	 */
	public static Map<String,List<WhereVO>> split(List<WhereVO> listWhere){
		Map<String,List<WhereVO>> result = new HashMap<String,List<WhereVO>>();
		if(listWhere == null) return result;
		for(WhereVO whereVO : listWhere){
			List<WhereVO> list = result.get(whereVO.getTable());
			if(list == null){
				list = new ArrayList<WhereVO>();
				result.put(whereVO.getTable(), list);
			}
			list.add(whereVO);
		}
		return result;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args){
		
		List<WhereVO> li= FastJsonUtils.getBeanList("[{\"listWhere\":[{\"field\":\"name\",\"condition\":\"OR\",\"table\":\"default\",\"operator\":\"%\",\"value\":\"1\"},{\"field\":\"email\",\"condition\":\"OR\",\"table\":\"default\",\"operator\":\"%\",\"value\":\"1\"},{\"field\":\"job_number\",\"condition\":\"OR\",\"table\":\"default\",\"operator\":\"%\",\"value\":\"1\"}],\"condition\":\"OR\"},{\"listwhere\":{\"field\":\"directorate_type\",\"operator\":\"=\",\"value\":\"0\"}}]", WhereVO.class);
		
		System.out.println(toSQL(li));
		
		
	}
}

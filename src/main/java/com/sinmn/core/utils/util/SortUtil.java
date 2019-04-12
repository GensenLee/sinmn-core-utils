package com.sinmn.core.utils.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
 
/**
 * 排序操作常用方法类.
 * 
 * @author zhangxh 2015/3/29
 */
public class SortUtil {

		
	/**
	 * 获取要更新的权重
	 * dao 必须实现5个接口
	 * 1. 获取位置
	 *    public int getPos(SearchVO svo); 
	 * 2. 获取位于最后面的vo
	 *    public VO getLastVO(SearchVO svo);
	 * 3. 获取位于最前面的vo
	 *    public VO getFirstVO(SearchVO svo);
	 * 4. 根据位置返回前后2个vo   
	 *    public List<VO> listPosVO(SearchVO svo);
	 * 5. 要更新的vo
	 *    public VO get(SearchVO svo);  or  public VO get(int id);
	 * @param dao
	 * @param SearchVO
	 * 
	 * @return 权重
	 * */
	public static float getSortWeight(Object dao,Object svo)
	{
		Class<?> daoClass = dao.getClass();
		Class<?> svoClass = svo.getClass();
		Class<?> idType = null;
		//先获取svo中 所需要提取的参数
		//pos 和  id
		Object id = null;
		Integer pos = 0;
		float result = 0;
		//获取修改后的排序权重
		try{
			Field field = svoClass.getDeclaredField("id");
			idType = field.getType();
			Method method = svoClass.getMethod("getId");
			id = method.invoke(svo);
			method = svoClass.getMethod("getPos");
			pos = (Integer)method.invoke(svo);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
		//数据库条目
		//获取排序权重修改前的数据
		Object vo = null;
		try{
			Method method = daoClass.getMethod("get", svo.getClass());
			vo = method.invoke(dao, svo);
		}catch(Exception e){
			try{
				Method method = daoClass.getMethod("get", idType);
				vo = method.invoke(dao, id);
			}catch(Exception ee){
				ee.printStackTrace();
				return 0;
			}
		}
		try{
			Method method = vo.getClass().getMethod("getSort_weight");
			//旧排序权重
			result = (Float)method.invoke(vo);
			
			//获取比原排序权重大的数据数目值
			method = daoClass.getMethod("getPos", svoClass);
			Integer oldPos = (Integer)method.invoke(dao, svo);
			if(pos != null){
				if(oldPos.intValue() == pos.intValue()){
					return result;
				}else{
					if(pos.intValue() == 1){
						//置顶
						method = daoClass.getMethod("getFirstVO", svoClass);
						Object firstVO = method.invoke(dao, svo);
						method = firstVO.getClass().getMethod("getSort_weight");
						result = (Float)method.invoke(firstVO) - 1f;
					}else if(pos.intValue() == -1){
						//埋底
						method = daoClass.getMethod("getLastVO", svoClass);
						Object lastVO = method.invoke(dao, svo);
						method = lastVO.getClass().getMethod("getSort_weight");
						result = (float)(Math.ceil((Float)method.invoke(lastVO)) + 1);
					}else{
						if(pos.intValue() > oldPos.intValue()){
							pos++;
						}
						try{
							method = svoClass.getMethod("setPos", int.class);
						}catch(Exception ee){
							method = svoClass.getMethod("setPos", Integer.class);
						}
						method.invoke(svo, pos-2);
						method = daoClass.getMethod("listPosVO", svoClass);
						Object listPos = method.invoke(dao, svo);
						try{
							method = listPos.getClass().getMethod("get",int.class);
						}catch(Exception ee){
							method = listPos.getClass().getMethod("get",Integer.class);
						}
						Method sizeFnc = listPos.getClass().getMethod("size");
						Integer size = (Integer)sizeFnc.invoke(listPos);
						if(size.intValue() == 1){
							//埋底
							Object first = method.invoke(listPos, 0);
							method = first.getClass().getMethod("getSort_weight");
							result = (float)(Math.ceil((Float)method.invoke(first)) + 1);
						}else if(size.intValue() == 2){
							Object first = method.invoke(listPos, 0);
							Object second = method.invoke(listPos, 1);
							method = first.getClass().getMethod("getSort_weight");
							result = ((Float)method.invoke(first) + (Float)method.invoke(second))/2;
						}
					}
				}
			}else{
				return result;
			}		
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return result;
	}


	public static float getListSortWeight(Object dao,Object svo)
	{
		Class<?> daoClass = dao.getClass();
		Class<?> svoClass = svo.getClass();
		Class<?> idType = null;
		//先获取svo中 所需要提取的参数
		//pos 和  id
		Object id = null;
		Integer pos = 0;
		float result = 0;
		//获取修改后的排序权重
		try{
			Field field = svoClass.getDeclaredField("id");
			idType = field.getType();
			Method method = svoClass.getMethod("getId");
			id = method.invoke(svo);
			method = svoClass.getMethod("getListPos");
			pos = (Integer)method.invoke(svo);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}

		//数据库条目
		//获取排序权重修改前的数据
		Object vo = null;
		try{
			Method method = daoClass.getMethod("get", svo.getClass());
			vo = method.invoke(dao, svo);
		}catch(Exception e){
			try{
				Method method = daoClass.getMethod("get", idType);
				vo = method.invoke(dao, id);
			}catch(Exception ee){
				ee.printStackTrace();
				return 0;
			}
		}
		try{
			Method method = vo.getClass().getMethod("getList_sort_weight");
			//旧排序权重
			result = (Float)method.invoke(vo);

			//获取比原排序权重大的数据数目值
			method = daoClass.getMethod("getListPos", svoClass);
			Integer oldPos = (Integer)method.invoke(dao, svo);
			if(pos != null){
				if(oldPos.intValue() == pos.intValue()){
					return result;
				}else{
					if(pos.intValue() == 1){
						//置顶
						method = daoClass.getMethod("getFirstListWeightVO", svoClass);
						Object firstVO = method.invoke(dao, svo);
						method = firstVO.getClass().getMethod("getList_sort_weight");
						result = (Float)method.invoke(firstVO) - 1f;
					}else if(pos.intValue() == -1){
						//埋底
						method = daoClass.getMethod("getLastListWeightVO", svoClass);
						Object lastVO = method.invoke(dao, svo);
						method = lastVO.getClass().getMethod("getList_sort_weight");
						result = (float)(Math.ceil((Float)method.invoke(lastVO)) + 1);
					}else{
						if(pos.intValue() > oldPos.intValue()){
							pos++;
						}
						try{
							method = svoClass.getMethod("setListPos", int.class);
						}catch(Exception ee){
							method = svoClass.getMethod("setListPos", Integer.class);
						}
						method.invoke(svo, pos-2);
						method = daoClass.getMethod("listPosVO", svoClass);
						Object listPos = method.invoke(dao, svo);
						try{
							method = listPos.getClass().getMethod("get",int.class);
						}catch(Exception ee){
							method = listPos.getClass().getMethod("get",Integer.class);
						}
						Method sizeFnc = listPos.getClass().getMethod("size");
						Integer size = (Integer)sizeFnc.invoke(listPos);
						if(size.intValue() == 1){
							//埋底
							Object first = method.invoke(listPos, 0);
							method = first.getClass().getMethod("getList_sort_weight");
							result = (float)(Math.ceil((Float)method.invoke(first)) + 1);
						}else if(size.intValue() == 2){
							Object first = method.invoke(listPos, 0);
							Object second = method.invoke(listPos, 1);
							method = first.getClass().getMethod("getList_sort_weight");
							result = ((Float)method.invoke(first) + (Float)method.invoke(second))/2;
						}
					}
				}
			}else{
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return result;
	}


	public static float getLastSortWeight(Object dao,Object svo)
	{
		Class<?> daoClass = dao.getClass();
		Class<?> svoClass = svo.getClass();
		try{
			Method method = daoClass.getMethod("getLastVO", svoClass);
			Object lastVO = method.invoke(dao, svo);
			if(lastVO == null){
				return 1;
			}else{
				method = lastVO.getClass().getMethod("getSort_weight");
				Float sort_weight = (Float)method.invoke(lastVO);
				return (float)(Math.ceil(sort_weight) + 1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}


	//分类列表权重
	public static float getLastListSortWeight(Object dao,Object svo)
	{
		Class<?> daoClass = dao.getClass();
		Class<?> svoClass = svo.getClass();
		try{
			Method method = daoClass.getMethod("getLastListWeightVO", svoClass);
			Object lastVO = method.invoke(dao, svo);
			if(lastVO == null){
				return 1;
			}else{
				method = lastVO.getClass().getMethod("getList_sort_weight");
				Float list_sort_weight = (Float)method.invoke(lastVO);
				return (float)(Math.ceil(list_sort_weight) + 1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}



	public static float getFirstSortWeight(Object dao,Object svo)
	{
		Class<?> daoClass = dao.getClass();
		Class<?> svoClass = svo.getClass();
		try{
			Method method = daoClass.getMethod("getFirstVO", svoClass);
			Object firstVO = method.invoke(dao, svo);
			if(firstVO == null){
				return 1;
			}else{
				method = firstVO.getClass().getMethod("getSort_weight");
				Float sort_weight = (Float)method.invoke(firstVO);
				//return (float)(Math.ceil(sort_weight) - 0.00001f);
				return (float)(Math.ceil(sort_weight) - 1f);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
	
	public static float getAddSortWeight(Object dao,Object svo)
	{
		Class<?> daoClass = dao.getClass();
		Class<?> svoClass = svo.getClass();
		try{
			Method method = daoClass.getMethod("getFirstVO", svoClass);
			Object firstVO = method.invoke(dao, svo);
			if(firstVO == null){
				return 1;
			}else{
				method = daoClass.getMethod("getLastVO", svoClass);
				Object lastVO = method.invoke(dao, svo);
				method = lastVO.getClass().getMethod("getSort_weight");
				Float sort_weight = (Float)method.invoke(lastVO);
				return (float)(Math.ceil(sort_weight) + 1f);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * 获取要更新的权重
	 * dao 必须实现5个接口
	 * 1. 获取位置
	 *    public int getPos(SearchVO svo); 
	 * 2. 获取位于最后面的vo
	 *    public VO getLastVO(SearchVO svo);
	 * 3. 获取位于最前面的vo
	 *    public VO getFirstVO(SearchVO svo);
	 * 4. 根据位置返回前后2个vo   
	 *    public List<VO> listPosVO(SearchVO svo);
	 * 5. 要更新的vo
	 *    public VO get(SearchVO svo);  or  public VO get(int id);
	 * @param dao
	 * @param SearchVO
	 * 
	 * @return 权重
	 * */
	public static float updateSortWeight(Object dao,Object svo)
	{
		Class<?> daoClass = dao.getClass();
		Class<?> svoClass = svo.getClass();
		Class<?> idType = null;
		//先获取svo中 所需要提取的参数
		//pos 和  id
		Object id = null;
		float pos = 0;
		float result = 0;
		//获取修改后的排序权重
		try{
			Field field = svoClass.getDeclaredField("id");
			idType = field.getType();
			Method method = svoClass.getMethod("getId");
			id = method.invoke(svo);
			method = svoClass.getMethod("getSort_weight");
			pos = (Float)method.invoke(svo);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
		//数据库条目
		//获取排序权重修改前的数据
		Object vo = null;
		try{
			Method method = daoClass.getMethod("get", svo.getClass());
			vo = method.invoke(dao, svo);
		}catch(Exception e){
			try{
				Method method = daoClass.getMethod("get", idType);
				vo = method.invoke(dao, id);
			}catch(Exception ee){
				ee.printStackTrace();
				return 0;
			}
		}
		try{
			Method method = vo.getClass().getMethod("getSort_weight");
			//旧排序权重
			result = (Float)method.invoke(vo);
			
			if(result == pos){
				return result;
			}
			
			//获取修改前和修改后的排序权重的列表值
			if(result > pos && pos != -1){
				
				method = svoClass.getMethod("setBefore_sort", float.class);
				method.invoke(svo, result);
				method = svoClass.getMethod("setAfter_sort", float.class);
				method.invoke(svo, pos);
				
				method = daoClass.getMethod("listBySort", svoClass);
				
				List<Object> goodsTypeList = (List<Object>) method.invoke(dao, svo);
				
				if(goodsTypeList != null){
					
					float sort_weight = 0;
					for(Object goodsType:goodsTypeList){
						method = goodsType.getClass().getMethod("getSort_weight");
						sort_weight = (Float)method.invoke(goodsType);
						if(sort_weight == result){
							continue;
						}
						method = goodsType.getClass().getMethod("setSort_weight",float.class);
						method.invoke(goodsType, sort_weight+1);
						method = daoClass.getMethod("updateBySort",goodsType.getClass());
						method.invoke(dao, goodsType);
					}
				}
			}
			
			if(result < pos){
				
				method = svoClass.getMethod("setBefore_sort", float.class);
				method.invoke(svo, pos);
				method = svoClass.getMethod("setAfter_sort", float.class);
				method.invoke(svo, result);
				
				method = daoClass.getMethod("listBySort", svoClass);
				
				List<Object> goodsTypeList = (List<Object>) method.invoke(dao, svo);
				
				if(goodsTypeList != null){
					
					float sort_weight = 0;
					for(Object goodsType:goodsTypeList){
						method = goodsType.getClass().getMethod("getSort_weight");
						sort_weight = (Float)method.invoke(goodsType);
						if(sort_weight == result){
							continue;
						}
						method = goodsType.getClass().getMethod("setSort_weight",float.class);
						method.invoke(goodsType,sort_weight-1);
						method = daoClass.getMethod("updateBySort",goodsType.getClass());
						method.invoke(dao, goodsType);
					}
				}
			}
			if(pos == -1f){
				//埋底
				method = daoClass.getMethod("getLastVO", svoClass);
				Object lastVO = method.invoke(dao, svo);
				method = lastVO.getClass().getMethod("getSort_weight");
				Float sort_weight_last = (Float)method.invoke(lastVO);
				return (float)(Math.ceil(sort_weight_last) + 1f);
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return pos;
	}
}

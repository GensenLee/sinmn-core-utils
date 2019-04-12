package com.sinmn.core.utils.vo;

import com.sinmn.core.utils.util.BeanUtil;
import com.sinmn.core.utils.util.FastJsonUtils;


public class BaseSearchVO extends BaseBean {

	private static final long serialVersionUID = -868608873665847029L;

	// 快速查询
	private String quickSearch;

	//是否分页
	private Boolean isPage = false;
	
	/**
	 * 记录查询开始
	 */
	private int    start  = 0;
	/**
	 * 一页大小
	 */
	private int    size   = 0;
	/**
	 * 当前页
	 */
	private int    page   = 1;
	/**
	 * 一页大小
	 */
	private int    displayRecord = 0;
	
	/**
	 * where条件
	 */
	private String where;
	
	public BaseSearchVO(){}
	
	public BaseSearchVO(String jsonString){
		BeanUtil.copy(FastJsonUtils.getBean(jsonString, this.getClass()), this);
	}
	
	
	public int getStart() {
		if(page<=0)
			page = 1;
		start = (page-1)*displayRecord;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		if(size<=0){
			if(displayRecord<=0)
				displayRecord = 20;
			size = displayRecord;
		}
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getDisplayRecord() {
		if(displayRecord<=0)
			displayRecord = 60;		
		return displayRecord;
	}
	public void setDisplayRecord(int displayRecord) {
		this.displayRecord = displayRecord;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	

	public Boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}

	public String getQuickSearch() {
		return quickSearch;
	}

	public void setQuickSearch(String quickSearch) {
		this.quickSearch = quickSearch;
	}

}

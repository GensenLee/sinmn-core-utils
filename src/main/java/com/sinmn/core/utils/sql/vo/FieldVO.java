package com.sinmn.core.utils.sql.vo;

import java.util.List;
import java.util.Map;

import com.sinmn.core.utils.util.MD5;
import com.sinmn.core.utils.vo.BaseBean;

public class FieldVO extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FieldVO(){}
	
	public FieldVO(String field,String comment,String type,String table,String url,List<Map<String,String>> values){
		this.field = field;
		if(comment == null){
			comment = "";
		}
		comment = comment.split("\\s")[0];
		this.comment = comment;
		this.type = type;
		this.table = table;
		this.url = url;
		this.values = values;
		this.field_alias = MD5.getMD5(field).replaceAll("-", "").toLowerCase();
		this.table_alias = MD5.getMD5(table).replaceAll("-", "").toLowerCase();
	}

	/**
	 * 原始字段名
	 */
	private String field;
	
	/**
	 * 加密后的字段名
	 */
	private String field_alias;
	
	/**
	 * 说明
	 */
	private String comment;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 表名
	 */
	private String table;
	
	/**
	 * 表加密后的字段
	 */
	private String table_alias;
	
	/**
	 * 数据源地址
	 */
	private String url;
	
	/**
	 * 值
	 */
	private List<Map<String,String>> values;
	
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getField_alias() {
		return field_alias;
	}

	public void setField_alias(String field_alias) {
		this.field_alias = field_alias;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Map<String, String>> getValues() {
		return values;
	}

	public void setValues(List<Map<String, String>> values) {
		this.values = values;
	}

	public String getTable_alias() {
		return table_alias;
	}

	public void setTable_alias(String table_alias) {
		this.table_alias = table_alias;
	}
	
	
	
}

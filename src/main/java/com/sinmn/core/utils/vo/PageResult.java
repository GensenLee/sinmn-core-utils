package com.sinmn.core.utils.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="PageResult",description="分页参数类")
@Data
public class PageResult<T> extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 数量
	 */
	@ApiModelProperty("总条数")
	private long count;
	
	/**
	 * 列表
	 */
	@ApiModelProperty(value = "列表",dataType = "List")
	private List<T> list;
	
	/**
	 * 获取一个新的PageResult
	 * @return
	 */
	public static <T> PageResult<T> get(){
		return new PageResult<T>();
	}

}

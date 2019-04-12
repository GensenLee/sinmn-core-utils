package com.sinmn.core.utils.exception;

import com.sinmn.core.utils.constant.ApiResultCode;

public class CommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code = ApiResultCode.UNKNOWN.getCode();
	
	public CommonException(){
		
	}
	
	public CommonException(String message){
		super(message);
	}
	
	public CommonException(ApiResultCode apiResultCode){
		super(apiResultCode.getMessage());
		this.setCode(apiResultCode.getCode());
	}
	
	
	public CommonException(int code,String message){
		super(message);
		this.setCode(code);
	}

	public CommonException(ApiResultCode apiResultCode ,String message){
		super(message);
		this.setCode(apiResultCode);
	}
	
	public CommonException(Throwable throwable){
		super(throwable);
		this.setCode(ApiResultCode.SYSTEM_ERROR.getCode());
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public void setCode(ApiResultCode apiResultCode) {
		this.code = apiResultCode.getCode();
	}
	
	

}

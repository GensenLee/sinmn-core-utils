package com.sinmn.core.utils.exception;

import com.sinmn.core.utils.constant.ApiResultCode;

public class CommonRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code = ApiResultCode.UNKNOWN.getCode();
	
	public CommonRuntimeException(){
		
	}
	
	public CommonRuntimeException(String message){
		super(message);
	}
	
	public CommonRuntimeException(ApiResultCode apiResultCode){
		super(apiResultCode.getMessage());
		this.setCode(apiResultCode.getCode());
	}
	
	
	public CommonRuntimeException(int code,String message){
		super(message);
		this.setCode(code);
	}

	public CommonRuntimeException(ApiResultCode apiResultCode ,String message){
		super(message);
		this.setCode(apiResultCode);
	}
	
	public CommonRuntimeException(Throwable throwable){
		super(throwable);
		this.setCode(ApiResultCode.SYSTEM_ERROR.getCode());
	}
	
	public CommonRuntimeException(String message,Throwable throwable){
		super(message,throwable);
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

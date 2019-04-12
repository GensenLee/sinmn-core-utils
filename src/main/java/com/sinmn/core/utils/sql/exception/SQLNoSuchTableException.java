package com.sinmn.core.utils.sql.exception;

import com.sinmn.core.utils.constant.ApiResultCode;
import com.sinmn.core.utils.exception.CommonRuntimeException;

public class SQLNoSuchTableException extends CommonRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SQLNoSuchTableException(String message){
		super(ApiResultCode.NO_SUCH_TABLE,message);
	}
	
	public SQLNoSuchTableException(String message,Throwable throwable){
		super(message,throwable);
	}

}

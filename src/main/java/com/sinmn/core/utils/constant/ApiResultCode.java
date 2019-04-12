package com.sinmn.core.utils.constant;

/**
 * 备注 50000后面的数字由每个系统自己定义
 * @author xhz
 *
 */
public enum ApiResultCode {

	 /**
	 * 未知错误
	 */
	UNKNOWN(-1,"未知错误"),
	 /**
	 * 成功
	 */
	SUCCESS(200,"成功"),
	
	 /**
	 * 系统异常
	 */
	SYSTEM_ERROR(500,"系统异常"),
	
	/**
	 * 参数格式错误
	 */
	PARAMETER_FORMAT_ERROR(400,"参数格式错误"),
	
	/**
	 * 参数验证出错
	 */
	PARAMETER_VERIFY_ERROR(401,"参数验证出错,传入的参数不符合API的要求"),
	
	/**
	 * 参数缺失
	 */
	PARAMETER_MISSING(402,"参数缺失"),
	
	/**
	 * 找不到版本对应的api
	 */
	NO_SUCH_VERSION_API(404,"找不到版本对应的api"),
	
	/**
	 * session_key错误
	 */
	SESSION_KEY_ERROR(20001,"session_key错误"),
	
	/**
	 * 无效的用户
	 */
	USER_INVALID(20002,"无效的用户"),
	
	/**
	 * 验证码错误
	 */
	VERIFY_CODE_ERROR(20004,"验证码错误"),
	
	/**
	 * 帐号密码错误
	 */
	USER_LOGIN_ERROR(20005,"帐号密码错误"),
	
	/**
	 * 无此权限
	 */
	PERMISSION_DENIED(20006,"无此权限"),
	
	/**
	 * 找不到该表
	 */
	NO_SUCH_TABLE(30001,"找不到该表");
	
	//50000 以上为系统自定义错误
	
	
	private int code;
	private String message;
	
	ApiResultCode(int code,String message){
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ApiResultCode getByCode(int code){
		for(ApiResultCode apiResultCode : ApiResultCode.values()){
			if(apiResultCode.getCode() == code){
				return apiResultCode;
			}
		}
		return null;
	}
	
	public static String getMessage(int code){
		ApiResultCode apiResultCode = getByCode(code);
		if(apiResultCode == null){
			return "";
		}
		return apiResultCode.getMessage();
	}
}

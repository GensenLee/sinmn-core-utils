package com.sinmn.core.utils.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Cookie操作类
 * 
 */
public class CookieUtil {
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private String path = "/"; // 默认路径
	
	private String domain = null; // 域
	
	private int maxAge = -1; // 最大有效期

	public CookieUtil(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	/**
	 * @param name
	 * @return
	 */
	public Cookie getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				//System.out.println(cookies[i].getName()+":"+cookies[i].getValue());
				if (name.equals(cookies[i].getName())) {
					return cookies[i];
				}
			}
		}
		return null;
	}

	/**
	 * 删除cookie
	 * 
	 * @param name
	 */
	public void deleteCookie(String name) {
		//setCookie(name, "", -1);
		setCookie(name, "", 1);
	}

	/**
	 * @param name
	 * @param value
	 */
	public void setCookie(String name, String value) {
		setCookie(name, value, maxAge);
	}

	/**
	 * 增加cookie
	 * 
	 * @param name
	 * @param value
	 * @param maxage
	 */
	public void setCookie(String name, String value, int cookieMaxAge) {
		setCookie(name, value, cookieMaxAge, path, domain);
	}

	/**
	 * 增加cookie
	 * 
	 * @param name
	 * @param value
	 * @param path
	 * @param domain
	 * @param cookieMaxAge
	 */
	public void setCookie(String name, String value, int cookieMaxAge, String path,
			String domain) {
		try {
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
			if (path != null) {
				cookie.setPath(path);
			} else {
				cookie.setPath("/");
			}
			if (domain != null) {
				cookie.setDomain(domain);
			}
			cookie.setMaxAge(cookieMaxAge);
	
			response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到COOKIE中的字符串
	 * 
	 * @param name
	 * @return
	 */
	public String getString(String name) {
		Cookie cookie = getCookie(name);
		if (cookie == null)
			return null;
		String value = cookie.getValue();
		if ("null".equals(value))
			return null;
		if (value != null) {
			try {
				value = URLDecoder.decode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * 返回整数型cookie值
	 * 
	 * @param name
	 * @return 异常返回－1
	 */
	public int getInt(String name) {
		return Integer.parseInt(getString(name)==null || "".equals(getString(name))?"-1":getString(name));
	}

	/**
	 * 返回浮点数cookie值
	 * 
	 * @param name
	 * @return 异常返回 -1.0d;
	 */
	public double getDouble(String name) {
		return Double.parseDouble(getString(name)==null || "".equals(getString(name))?"-1":getString(name));
	}
	
	/**
	 * 设置默认的PATH
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 设置默认的DOMAIN
	 * 
	 * @param domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * 设置默认的MAX_AGE
	 * 
	 * @param age
	 */
	public void setMaxAge(int age) {
		this.maxAge = age;
	}

	public static void main(String[] args) {

	}
}

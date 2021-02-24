package com.lxc.base.bean;

/**
 * 请求响应实体
 */
public class ResponseBody<T> {
	
	/**
	 * 响应码
	 */
	public int code;
	
	/**
	 * 响应标题
	 */
	public String title;
	
	/**
	 * 响应信息
	 */
	public T data;
}

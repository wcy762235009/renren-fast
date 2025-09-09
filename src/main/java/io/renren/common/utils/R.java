/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.utils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(
			value = "返回标记：成功标记=0，失败标记=1"
	)
	private int code;
	@ApiModelProperty(
			value = "返回信息"
	)
	private String msg;
	@ApiModelProperty(
			value = "数据"
	)
	private T data;

	public R() {
	}

	public R(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	static <T> R<T> restResult(T data, int code, String msg) {
		R<T> apiResult = new R<T>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

	private R<T> setMsg(String msg) {
		this.msg=msg;
		return this;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

	private R<T> setData(T data) {
		this.data=data;
		return this;
	}

	private R<T> setCode(int code) {
		this.code=code;
		return this;
	}

	public static <T> R<T>  error() {
		return restResult(null, HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static <T> R<T> error(String msg) {
		return restResult(null, HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static <T> R<T> error(T data, int code, String msg) {
		return restResult(data, code, msg);
	}
	public static <T> R<T> error(int code, String msg) {
		return restResult(null, code, msg);
	}
	public static <T> R<T> error(T data, String msg) {
		return restResult(data, 1, msg);
	}

	
	public static <T> R<T> success(T data) {
		return restResult(data, 0, "success");
	}
	public static <T> R<T> success(int code,T data,String msg) {
		return restResult(data, code, msg);
	}
	public static <T> R<T> success(int code) {
		return restResult(null, code, "success");
	}
	public static <T> R<T> success(String msg) {
		return restResult(null, 0, msg);
	}
	
	public static <T> R<T> success() {
		return restResult(null, 0, "success");
	}


}

/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.exception;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import io.renren.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
@RestController
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String REQUEST_VALIDATION_ERRORS = "请求参数验证错误";
	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R handleRRException(RRException e){
		log.error("业务异常 ex={}",e.getMessage(),e);
		return R.error(e.getCode(), e.getMsg());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		return R.error(details, REQUEST_VALIDATION_ERRORS);
	}

	/**
	 * RequestParam参数的校验
	 *
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public R processException(ConstraintViolationException e) {
		String msg = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(StringPool.SEMICOLON));
		return R.error(msg);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public R handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("校验或者参数错误 ex={}",e.getMessage(),e);
		return R.error(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
}

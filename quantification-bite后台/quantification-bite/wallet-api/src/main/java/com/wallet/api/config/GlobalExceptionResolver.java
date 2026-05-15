package com.wallet.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.wallet.common.core.domain.AjaxResult;
import com.wallet.common.core.domain.ErrMessage;
import com.wallet.common.exception.TicketException;
import com.wallet.common.utils.StringUtils;


/**
 * 全局异常处理 \* @author
 * 
 * @date: 2018年9月9日 下午10:52:55
 */
@RestControllerAdvice
public class GlobalExceptionResolver {
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public AjaxResult missingServletRequestParameterException(MissingServletRequestParameterException e) {
		String msg = e.getMessage();
		logger.info("=============输入参数异常：{}", msg);
		return AjaxResult.error(msg.substring(msg.indexOf("'") + 1, msg.lastIndexOf("'")) + "is null");
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public AjaxResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		String msg = e.getMessage();
		logger.info("=============输入参数异常：{}", msg);
		return AjaxResult.error("Incorrect parameter type");
	}
	
	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(BindException.class)
	public AjaxResult validatedBindException(BindException e) {
		logger.error("自定义验证异常", e);
		String message = e.getAllErrors().get(0).getDefaultMessage();
		return AjaxResult.error(message);
	}

	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public AjaxResult handleException(HttpRequestMethodNotSupportedException e) {
		logger.error("请求方式不支持异常:", e);
		return AjaxResult.error("Not Supported' " + e.getMethod() + "'request");
	}

	/**
	 * 系统异常
	 */
	@ExceptionHandler(Exception.class)
	public AjaxResult handle(Exception e) {
		logger.error("系统异常:", e);
		return AjaxResult.error("network busy");
	}
	@ExceptionHandler(RuntimeException.class)
	public AjaxResult notFount(RuntimeException e) {
		logger.error("运行时异常:", e);
		if (StringUtils.isNotEmpty(e.getMessage())) {
			return AjaxResult.error(e.getMessage());
		}
		return AjaxResult.error("network busy");
	}
	@ExceptionHandler(TicketException.class)
	public AjaxResult TicketError(TicketException e) {
		logger.error("ticket有误:", e);
		return AjaxResult.error(ErrMessage.CODE_TICKET_ERROR);
	}

	
}

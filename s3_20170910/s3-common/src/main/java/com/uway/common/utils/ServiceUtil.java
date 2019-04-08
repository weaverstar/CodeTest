package com.uway.common.utils;

import org.springframework.context.MessageSource;

import com.uway.common.response.Response;

public class ServiceUtil {

	/**
	 * Description: 设置response的错误信息
	 * @param  Response response   返回的response
	 *         MessageSource messageSource   spring的资源文件
	 *         String interfaceKey           接口名称
	 *         String errorValue             错误信息
	 *         Integer language              语言
	 * @Author:fengchao
	 * @Create Date: 2015-11-16
	 */
	public static void setErrorInfo(Response response,MessageSource messageSource,String interfaceKey,String errorValue,Integer language){
		
		String msg = null;
		msg = MessageUtils.getMessage(messageSource, interfaceKey + errorValue, null,language);
		response.setErrorCount(1);
		response.setSuccessCount(0);
		response.addErrInfo(errorValue, msg, null);
	}
	
	
	
	
}

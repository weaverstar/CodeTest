package com.uway.common.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String 工具类
 * 
 * @author fanchangfei
 */
public class Base64Util {

	private final static Logger logger = LoggerFactory.getLogger(Base64Util.class);

	public static void main(String[] args) {
		System.out.println(convertToByte(null));
	}
	
	/**
	 * FunName:convertNickNameToBytes Description:将字符串转换成二进制码的字符串
	 * 
	 * @param:nickName
	 * @return String
	 * @Author:fanchangfei
	 * @Create Date: 2014-7-30
	 */
	public static String convertToByte(String str) {
		try {
			if (StringUtils.isNotBlank(str)) {
				byte[] bs = str.getBytes("UTF-8");
				String result = new sun.misc.BASE64Encoder().encodeBuffer(bs);
				return result;
			}
		} catch (Exception e) {
			logger.error("转为二进制，转码异常", e);
		}
		return null;

	}

	/**
	 * FunName:convertNickNameToString Description:将二进制码的字符串还原成真实的字符串
	 * 
	 * @param:nickName
	 * @return String
	 * @Author:fanchangfei
	 * @Create Date: 2014-7-30
	 */
	public static String convertToString(String str) {
		try {
			if (StringUtils.isNotBlank(str)) {
				byte[] bt;
				bt = new sun.misc.BASE64Decoder().decodeBuffer(str);
				str = new String(bt, "UTF-8");
				return str;
			}
		} catch (IOException e) {
			logger.error("转为字符串，转码异常", e);
		}
		return null;
	}

}

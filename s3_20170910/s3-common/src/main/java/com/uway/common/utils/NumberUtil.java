package com.uway.common.utils;

import java.math.BigDecimal;

/**
 * 数字转换工具类
 * @author bwang
 *
 */
public class NumberUtil {
	
	/**
	 * 将对象numStr转换成整型,转换失败时返回指定值defVal
	 * @param numStr	被转换的对象
	 * @param defVal	转换失败返回的默认值
	 * @return
	 */
	public static int parseInt(Object numStr, int defVal) {
		if(null == numStr) {
			return defVal;
		}
		try {
			return Integer.parseInt(String.valueOf(numStr));
		} catch (NumberFormatException e) {
			return defVal;
		}
	}
	
	/**
	 * 将对象numStr转换成长整型,转换失败时返回指定值defVal
	 * @param numStr	被转换的对象
	 * @param defVal	转换失败返回的默认值
	 * @return
	 */
	public static long parseLong(Object numStr, long defVal) {
		if(null == numStr) {
			return defVal;
		}
		try {
			return Long.parseLong(String.valueOf(numStr));
		} catch (NumberFormatException e) {
			return defVal;
		}
	}
	
	/**
	 * 将对象numStr转换成单精度浮点型,转换失败时返回指定值defVal
	 * @param numStr	被转换的对象
	 * @param defVal	转换失败返回的默认值
	 * @return
	 */
	public static float parseFloat(Object numStr, float defVal) {
		if(null == numStr) {
			return defVal;
		}
		try {
			return Float.parseFloat(String.valueOf(numStr));
		} catch (NumberFormatException e) {
			return defVal;
		}
	}
	
	/**
	 * 将对象numStr转换成双精度浮点型,转换失败时返回指定值defVal
	 * @param numStr	被转换成的对象
	 * @param defVal	转换失败返回的默认值
	 * @return
	 */
	public static double parseDouble(Object numStr, double defVal) {
		if(null == numStr) {
			return defVal;
		}
		try {
			return Double.parseDouble(String.valueOf(numStr));
		} catch (NumberFormatException e) {
			return defVal;
		}
	}
	
	/**
	 * 将numStr按指定的精度进行转换
	 * @param numStr	被转换成的对象
	 * @param scale		精度
	 * @return
	 */
	public static double convert(Object numStr, int scale) {
		if(numStr == null) {
			return 0;
		}
		try {
			return new BigDecimal(String.valueOf(numStr)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}

package com.uway.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

/**
 * 相同属性复制工具类
 * @author dy
 *
 */
public class BeanCopierUtils {
	private static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

	/**
	 * 不同对象的相同名称属性复制方法
	 * @param source 源对象
	 * @param target 目标对象
	 * @param isDateToString 是否需要将Date类型的属性转换成字符串格式，true：是      
	 * @author dingyang
	 * @Date: 2017-1-23 下午5:00:38
	 */
	public static void copyProperties(Object source, Object target, boolean isDateToString) {
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier = null;
		if (!beanCopierMap.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), isDateToString);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = beanCopierMap.get(beanKey);
		}
		copier.copy(source, target, isDateToString ? new DateConverterBeanCopier() : null );
		
		//copier.copy(source, target, new DateConverterBeanCopier());
	}

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}

}

//Date日期格式转换  ，输入Date 格式 ,输出 String
class DateConverterBeanCopier implements net.sf.cglib.core.Converter {
	public Object convert(Object arg1, Class arg0, Object context) {
		if (arg1 instanceof String && arg0 != String.class) {
			String p = (String) arg1;
			if (p == null || p.trim().length() == 0) {
				return null;
			}
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.parse(p.trim());
			} catch (Exception e) {
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					return df.parse(p.trim());
				} catch (ParseException ex) {
					return arg1;
				}
			}
		}else if (arg1 instanceof String && arg0 == String.class) {/** 输入String ,输出String */
			return arg1;
		}else if (arg1 instanceof java.util.Date) {/** 输入Date ,输出 String*/
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.format((java.util.Date) arg1);
			} catch (Exception e) {
				return null;
			}
		}else if (arg1 instanceof java.sql.Date) {/** 输入Date ,输出String */
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.format((java.sql.Date) arg1);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
}

package com.uway.core.util;

/**
 * 字符串转换
 */
public class StringUtil {
    /**
     * 将对象str转换成整型,转换失败时返回指定值def
     * @param str	被转换的对象
     * @param def	转换失败返回的默认值
     * @return
     */
    public static String toString(Object str,String def){
        return str == null ? def : str.toString();
    }
}

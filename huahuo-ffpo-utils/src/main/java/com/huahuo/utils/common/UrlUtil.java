package com.huahuo.utils.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @作者 花火
 * @创建日期 2022/9/29 11:07
 */


public class UrlUtil {

    public static String removePrefix(String str, String prefix) {
        if (StringUtils.isEmpty(str)) {
            return "";
        } else {
            if (null != prefix) {
                String prefixArray = prefix;


                    String pf = prefixArray;
                    if (str.toLowerCase().matches("^" + pf.toLowerCase() + ".*")) {
                        return str.substring(pf.length());//截取前缀后面的字符串
                    }

            }

            return str;
        }
    }
}
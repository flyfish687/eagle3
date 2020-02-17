package com.dfsoft.utils;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Json数据与字符串的转换util
 */
public class JsonUtil {

    /**
     * Json to string string.
     * 判断json数据内是否包含相应字段并获取相应字段
     *
     * @param json   json数据
     * @param string 需要获取的字符数据
     * @return 获取的字符json数据
     * @author : junhua / 2016-09-20
     */
    public static String getString(String json, String string) {
        boolean contains = containsString(json, string);
        if (contains) {
            string = JSONObject.parseObject(json).getString(string);
        } else {
            string = null;
        }
        return string;
    }

    private static boolean containsString(String json, String string) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Boolean contains = jsonObject.containsKey(string);
        return Boolean.TRUE.equals(contains);
    }

    public static String toJson(Object object, String dataFormatString) {
        if (null != object) {
            if (StringUtils.isBlank(dataFormatString)) {
                JSONObject.toJSONString(object);
            }
            return JSON.toJSONStringWithDateFormat(object, dataFormatString);
        } else {
            return null;
        }
    }

    public static String toJson(Object object) {
        if (null != object) {
            return JSONObject.toJSONString(object);
        } else {
            return null;
        }
    }
}

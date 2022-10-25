package com.huahuo.utils.common;



import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @作者 花火
 * @创建日期 2022/10/4 22:28
 */


public class JSONUtils {
    public static HashMap<String, String> JsonObjectToHashMap(JSONObject jsonObj){
        HashMap<String, String> data = new HashMap<String, String>();
        Iterator it = jsonObj.keys();
        while(it.hasNext()){            String key = String.valueOf(it.next().toString());
            String value = (String)jsonObj.get(key).toString();
            data.put(key, value);
        }
        System.out.println(data);
        return data;
    }
}
package com.ljb.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
public class JsonFilterUtils {
 
    //递归对象
    public static JSONObject changeObjectUrlMsg(String suffix,JSONObject jsonObject) {
    	if(jsonObject.containsKey("url")) {
    		updateUrl(suffix,jsonObject);
    	}
    	if(jsonObject.containsKey("list")) {
    		JSONArray array=jsonObject.getJSONArray("list");
    		for(int i=0;i<array.size();i++) {
    			if(array.getJSONObject(i).containsKey("url")) {
    				updateUrl(suffix,array.getJSONObject(i));
    			}
    		}
    	}
    	return jsonObject;
    }
 
    public static void updateUrl(String suffix, JSONObject jsonObject) {
    	String url=jsonObject.getString("url");
		url=suffix+(url=="null"?"":url);
		jsonObject.remove("url");
		jsonObject.put("url", url);
    }
}


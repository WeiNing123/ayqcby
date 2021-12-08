package com.project.ayqcby.tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ResponseResult {
    public static JSONObject success(){
        JSONObject js = new JSONObject();
        js.put("code", 0);
        return js;
    }
    public static JSONObject error(){
        JSONObject js = new JSONObject();
        js.put("code", -1);
        return js;
    }
    public static JSONObject msg(String msg){
        JSONObject js = new JSONObject();
        js.put("msg", msg);
        return js;
    }
    public static JSONObject lose(){
        JSONObject js = new JSONObject();
        js.put("code", 401);
        js.put("data", new JSONArray());
        return js;
    }
}

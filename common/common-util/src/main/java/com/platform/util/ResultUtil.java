package com.platform.util;

import com.alibaba.fastjson.JSON;
import com.platform.base.RetCode;
import com.platform.vo.JsonResult;

public class ResultUtil {

    public static JsonResult ok(){
        return new JsonResult(RetCode.SUCCESS);
    }

    public static JsonResult ok(Object data){
        return new JsonResult(RetCode.SUCCESS,data);
    }

    public static JsonResult error(RetCode retCode){
        return new JsonResult(retCode);
    }

    public static JsonResult error(RetCode retCode,String msg){
        return new JsonResult(retCode,msg);
    }

    public static <T>T parseRetData(JsonResult jsonResult,Class<T> clazz){
        Object object = jsonResult.getRetdata();
        if(object!=null){
            return JSON.parseObject(JSON.toJSONString(object), clazz);
        }
        return null;
    }
}

package com.platform.exception;

import com.platform.base.RetCode;
import com.platform.vo.JsonResult;

/**
 * 自定义的异常返回
 */
public class ResponseExpection extends RuntimeException {

    private JsonResult jsonResult;

    public ResponseExpection(RetCode retCode){
        super(retCode.getMsg());
        jsonResult = new JsonResult(retCode);
    }

    public ResponseExpection(RetCode retCode, String retMsg){
        super(retMsg);
        jsonResult = new JsonResult(retCode,retMsg);
    }

    public ResponseExpection(String retMsg){
        super(retMsg);
        jsonResult = new JsonResult(RetCode.ERROR,retMsg);
    }

    public JsonResult getJsonResult(){
        return jsonResult;
    }


}

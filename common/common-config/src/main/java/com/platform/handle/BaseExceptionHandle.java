package com.platform.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.platform.base.RetCode;
import com.platform.exception.ResponseExpection;
import com.platform.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@ControllerAdvice
public class BaseExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(BaseExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult handle(Exception e) {
        logger.error(e.getMessage(),e);
        JsonResult ret = null;
        if(e instanceof JSONException){
            //json解析错误
            ret = new JsonResult(RetCode.ERROR,"json字符串解析错误");
        }else if(e instanceof ResponseExpection){
            ret = ((ResponseExpection) e).getJsonResult();
        }else if(e instanceof MethodArgumentNotValidException){
            StringBuffer sb = new StringBuffer();
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            Iterator var2 = bindingResult.getAllErrors().iterator();
            while(var2.hasNext()) {
                ObjectError error = (ObjectError)var2.next();
                sb.append(error.getDefaultMessage());
                break;
            }
            ret = new JsonResult(RetCode.ERROR,sb.toString());
        }else{
            ret = new JsonResult(RetCode.ERROR,e.getMessage());
        }
        //异常时的打印日志
        logger.info("返回参数：{}", JSON.toJSONString(ret));
        return ret;
    }
}
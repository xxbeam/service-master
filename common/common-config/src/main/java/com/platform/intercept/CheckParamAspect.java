package com.platform.intercept;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用参数校验aop
 */
@Component
@Aspect
@Order(1)
public class CheckParamAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @Around(value = "@annotation(com.platform.annotation.CheckParam)") // 这里要换成自定义注解的路径
    public Object check(ProceedingJoinPoint point) throws Throwable {
        Object obj;
        // 参数校验
        logger.info("请求参数：{}",(String)point.getArgs()[0]);
        // 通过校验，继续执行原有方法
        obj = point.proceed();

        logger.info("返回参数：{}",JSON.toJSONString(obj));
        return obj;
    }


}
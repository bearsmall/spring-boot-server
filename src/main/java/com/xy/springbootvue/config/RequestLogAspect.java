package com.xy.springbootvue.config;

import com.alibaba.fastjson.JSON;
import com.xy.springbootvue.pojo.OperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class RequestLogAspect {
  private ThreadLocal<OperationLog> logThreadLocal = new ThreadLocal<>();
  //拦截web下所有方法
  @Pointcut("execution(* com.xy.springbootvue.web..*.*(..))")
  public void pointcut() {
    System.out.println("拦截请求start");
  }

  @Before("pointcut()")
  public void doBefore(JoinPoint joinPoint) {

    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    String beanName = joinPoint.getSignature().getDeclaringTypeName();
    String methodName = joinPoint.getSignature().getName();
    String uri = request.getRequestURI();
    //get方法不记录日志
    if ("GET".equals(request.getMethod())) {
      return;
    }
    //请求参数
    Object[] paramsArray = joinPoint.getArgs();

    // 组装日志数据
    OperationLog optLog = new OperationLog();
    optLog.setResource(uri);
    optLog.setRequestMethod(request.getMethod());
    optLog.setBeanName(beanName);
    optLog.setMethodName(methodName);
    optLog.setRequestParams(argsArrayToString(paramsArray));
    logThreadLocal.set(optLog);
  }

  @AfterReturning(returning = "result", pointcut = "pointcut()")
  public void doAfterReturning(Object result) {
    try {
      // 处理完请求，从线程变量中获取日志数据，并记录到db
      OperationLog optLog = logThreadLocal.get();
      if (null != optLog) {
        optLog.setResponseData(JSON.toJSONString(result));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 清除threadlocal
      logThreadLocal.remove();
    }
  }

  /**
   * 请求参数拼装
   *
   * @param paramsArray
   * @return
   */
  private String argsArrayToString(Object[] paramsArray) {
    String params = "";
    if (paramsArray != null && paramsArray.length > 0) {
      for (int i = 0; i < paramsArray.length; i++) {
        Object jsonObj = JSON.toJSON(paramsArray[i]);
        params += jsonObj.toString() + " ";
      }
    }
    return params.trim();
  }
}
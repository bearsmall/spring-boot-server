package com.xy.springbootvue.pojo;

public class OperationLog{
  private String userId; // 操作人
  private String resource; // 操作的资源
  private String requestMethod; // 请求方式
  private String beanName; // 操作的类
  private String methodName; // 操作的模块
  private String requestParams; // 请求的参数
  private String responseData; // 返回数据

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getRequestMethod() {
    return requestMethod;
  }

  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  public String getBeanName() {
    return beanName;
  }

  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public String getRequestParams() {
    return requestParams;
  }

  public void setRequestParams(String requestParams) {
    this.requestParams = requestParams;
  }

  public String getResponseData() {
    return responseData;
  }

  public void setResponseData(String responseData) {
    this.responseData = responseData;
  }

  @Override
  public String toString() {
    return "OperationLog{" +
            "userId='" + userId + '\'' +
            ", resource='" + resource + '\'' +
            ", requestMethod='" + requestMethod + '\'' +
            ", beanName='" + beanName + '\'' +
            ", methodName='" + methodName + '\'' +
            ", requestParams='" + requestParams + '\'' +
            ", responseData='" + responseData + '\'' +
            '}';
  }
}
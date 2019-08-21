package com.github.wxiaoqi.security.common.exception;

public class BizException extends BaseException {

  private int status = -1;

  public BizException() {
    super.setStatus(this.status);
  }


  public BizException(String message, int status) {
    super(message);
    this.status = status;
  }


  public BizException(String message) {
    super(message);
    super.setStatus(this.status);
  }


  public BizException(String message, Throwable cause) {
    super(message, cause);
    super.setStatus(this.status);
  }


  public BizException(Throwable cause) {
    super(cause);
    super.setStatus(this.status);
  }


  public BizException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    super.setStatus(this.status);
  }


  @Override
  public int getStatus() {
    return status;
  }


  @Override
  public void setStatus(int status) {
    this.status = status;
  }


}

package com.xpertcaller.server.exception.userdefined;

public class BusinessException extends Exception{
    public BusinessException(String errorMessage){
        super(errorMessage);
    }
    public BusinessException(){
        super();
    }
    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
    }
}

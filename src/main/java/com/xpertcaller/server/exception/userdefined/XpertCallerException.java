package com.xpertcaller.server.exception.userdefined;

public class XpertCallerException extends Exception{
    public XpertCallerException(String errorMessage){
        super(errorMessage);
    }
    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
    }
}

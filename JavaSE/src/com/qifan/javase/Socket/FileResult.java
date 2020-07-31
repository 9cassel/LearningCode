package com.qifan.javase.Socket;

import java.io.Serializable;

public class FileResult implements Serializable {

    private String resultType = null;
    private Object result = null;
    private int code = 0;
    private String message = null;

    public FileResult(String resultType, Object result) {
        this.resultType = resultType;
        this.result = result;
    }

    public FileResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

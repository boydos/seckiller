package com.seckiller.dto;

public class SeckillerResult<T> {

    private boolean success;

    private T data;

    private String msg;

    public SeckillerResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillerResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

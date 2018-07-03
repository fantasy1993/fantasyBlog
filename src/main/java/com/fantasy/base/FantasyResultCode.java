package com.fantasy.base;

public enum FantasyResultCode {

    SUCCESS(00,"请求成功"),
    WARN(-1,"网络异常，请稍后重试");

    private int code;
    private String msg;

    FantasyResultCode(int code,String msg){
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

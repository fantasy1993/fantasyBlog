package com.fantasy.base;

public class FantasyResult {
    private int code;
    private String msg;
    private Object data;

    public FantasyResult(FantasyResultCode fantasyResultCode,Object data){
        this(fantasyResultCode);
        this.data = data;
    }

    public FantasyResult(FantasyResultCode fantasyResultCode){
        this.code = fantasyResultCode.getCode();
        this.msg = fantasyResultCode.getMsg();
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

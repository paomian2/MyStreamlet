package com.linxz.streamlet.base;

/**
 * <p>
 * Function： 网络请求返回基本数据类型
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年01月22日  10:53	lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class BaseResponse {

    /** 序列号 */
    private static final long serialVersionUID = 6945319206585873015L;

    private boolean Result;
    private String State="";
    private String Dynamic="";
    private String Msg="";

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean result) {
        Result = result;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}

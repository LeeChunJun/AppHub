package com.licj.apphub.commons.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by licj on 2018/3/8.
 */

public class BaseModel {

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;


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

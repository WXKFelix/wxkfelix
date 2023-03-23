package com.example.demo.vo;



import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * ClassName: Result
 * Author: wangxiaokang
 * Date: 3/16/2023 5:39 PM
 * History:
 * <author>       <time>       <version>       <desc>
 * wangxiaokang   修改时间       1.0.0          备注信息
 */
public class Result implements Serializable {
    private static final long serialVersionUID = -6106254133542839266L;
    private Integer code;
    private String message;
    private String str;
    private Object data;

    public Result() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Result(Integer code, String message, String str, Object data) {
        this.code = code;
        this.message = message;
        this.str = str;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", str='" + str + '\'' +
                ", data=" + data +
                '}';
    }
}

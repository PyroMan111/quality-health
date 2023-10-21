package com.woniuxy.system.common.enums.impl;


import com.woniuxy.system.common.enums.IResultCode;
import lombok.Getter;

/**
 * 通用状态码
 * */

@Getter
public enum ResultCode implements IResultCode {
    //枚举对象：状态码、描述
    SUCCESS(200,"操作成功"),
    FAIL(500,"操作失败"),


    /**
     * 参数错误: 30001~39999
     * */
    PARAM_IS_INVALID(30001,"参数无效"),
    PARAM_IS_BLANK(30002,"参数为空"),
    PARAM_TYPE_BIND_ERROR(30003,"参数类型错误");

    private int code;
    private String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }


}

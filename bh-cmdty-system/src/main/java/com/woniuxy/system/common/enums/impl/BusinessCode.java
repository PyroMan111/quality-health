package com.woniuxy.system.common.enums.impl;

import com.woniuxy.system.common.enums.IResultCode;
import lombok.Getter;

@Getter
public enum BusinessCode implements IResultCode {
    //枚举对象：状态码、描述
    VERRIFY_CODE_IS_NULL(21000, "验证码为空"),
    VERRIFY_CODE_ERROR(21001, "验证码错误"),
    LOGIN_FAILED(21002, "用户名或密码错误，登录失败"),

    USER_EXISTS(21003, "账户已经存在，注册失败"),

    EMAIL_EXISTS(21004, "邮箱已经存在，注册失败"),
    STORAGE_IS_NOT_ENOUGH(21005,"库存不足！" ),
    ORDER_REPEAT_SUBMIT(21006,"订单重复提交！请稍后再试" );



    private int code;
    private String message;

    BusinessCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}

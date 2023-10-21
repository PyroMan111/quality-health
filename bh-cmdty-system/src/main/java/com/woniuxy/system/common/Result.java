package com.woniuxy.system.common;

import com.woniuxy.system.common.enums.IResultCode;
import com.woniuxy.system.common.enums.impl.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回结果
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {



    private int code;
    private String message;
    private T data;

    public static <T> Result<T> ok(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }


    public static <T> Result<T> ok() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAIL.getCode(),ResultCode.FAIL.getMessage(), null);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultCode.FAIL.getCode(),message, null);
    }



    public static <T> Result<T> fail(IResultCode resultCode) {

        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }


}
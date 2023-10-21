package com.woniuxy.system.common.exception;


import com.woniuxy.system.common.enums.IResultCode;

/**
 * 自定义的断言类
 */
public abstract class Asserts {
    public static void error(boolean flag, IResultCode resultCode) {
        if (flag) {
            throw new BusinessException(resultCode);
        }
    }
}
package com.woniuxy.cxy.common.exception;

//import com.woniuxy.portal.common.enums.IResultCode;

import com.woniuxy.cxy.common.enums.IResultCode;

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
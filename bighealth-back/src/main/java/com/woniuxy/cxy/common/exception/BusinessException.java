package com.woniuxy.cxy.common.exception;

//import com.woniuxy.portal.common.enums.IResultCode;

import com.woniuxy.cxy.common.enums.IResultCode;

public class BusinessException extends RuntimeException {

    private IResultCode resultCode;

    public IResultCode getResultCode() {
        return resultCode;
    }


    public BusinessException() {
    }

    public BusinessException(IResultCode resultCode) {
        this.resultCode = resultCode;
    }
}

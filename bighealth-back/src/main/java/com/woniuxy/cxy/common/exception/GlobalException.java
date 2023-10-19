package com.woniuxy.cxy.common.exception;

//import com.woniuxy.portal.common.Result;
//import com.woniuxy.portal.common.enums.IResultCode;
//import com.woniuxy.portal.common.enums.impl.ResultCode;
import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.common.enums.IResultCode;
import com.woniuxy.cxy.common.enums.impl.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    private ResultCode resultCode;


    /**
     * 指定要处理的异常类型
     * */
    @ExceptionHandler(BusinessException.class)
    public Result handlerBusinessException(BusinessException e){
        e.printStackTrace();
        IResultCode resultCode = e.getResultCode();
        return Result.fail(resultCode);
    }


    /**
     * 如果没有精确匹配的异常，则走最大的异常
     * */
    @ExceptionHandler(Exception.class)

    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.fail(ResultCode.FAIL);
    }

}

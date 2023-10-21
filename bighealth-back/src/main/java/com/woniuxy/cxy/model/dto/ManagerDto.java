package com.woniuxy.cxy.model.dto;

import lombok.Data;
/**
 * 封装登录请求参数
 */
@Data
public class ManagerDto {
    private String userName;
    private String userPassword;
}
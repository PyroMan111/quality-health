package com.woniuxy.cxy.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemVo {
    private Long id;
    private String name;        //名称
    private String img;         //图片
    private Integer bcount;     //数量
    private BigDecimal sumprice;//小计
}
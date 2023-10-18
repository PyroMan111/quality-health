package com.woniuxy.cxy.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemVO {
    private Long id;
    private String bookName;//名称
    private String bookImg;//图片
    private Integer bcount;//数量
    private BigDecimal sumprice;//小计
}
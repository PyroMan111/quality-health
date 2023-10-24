package com.woniuxy.cxy.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityAdvancedQueryVo {
    private String name;
    private Long categoryId;
    private Integer status;
    private String code;
    private BigDecimal lowPrice;
    private BigDecimal highPrice;
    private Integer pageNum;
    private Integer pageSize;
}

package com.woniuxy.cxy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemVo {
    // 商品ID
    private Long id;
    // 商品名称
    private String name;
    // 商品图片
    private String imgsrc;
    // 商品单价
    private BigDecimal price;
    // 购买数量
    private Integer buycount;
    // 小计 = 单价 * 数量
    private BigDecimal sumPrice;

//    购买这件商品的用户Id
    private String UserId ;

}


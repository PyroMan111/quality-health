package com.woniuxy.cxy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVO {
//    private Long id;
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    private String orderNum;
    private BigDecimal totalprice;
    private String orderStatus;//1 2 3 4
    private String receiver;//收货人
    //一个订单包含多个订单详情
    private List<OrderItemVO> orderItem;//订单详情
}
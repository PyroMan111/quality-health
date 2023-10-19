package com.woniuxy.cxy.common.constant;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NO_PAY(1, "待付款"),
    NO_SEND(2, "待发货"),
    NO_RECEIVE(3, "待收货"),
    NO_EVALUATION(4, "待评价"),
    RETURNING_GOODS(5, "退货中"),
    RETURNING_MONEY(6, "退款中"),
    ALREADY_CANCEL(7, "已取消");
    private Integer code;
    private String describe;

    OrderStatusEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
}
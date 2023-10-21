package com.woniuxy.cxy.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Getter
@Setter
@TableName("h_aftermarket")
@ApiModel(value = "Aftermarket对象", description = "")
public class Aftermarket implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderNumId;

    private BigDecimal refund;

    private Date createTime;

    private Date handleTime;

    private Integer handleState;

    private Date updateTime;

    @TableLogic
    private Integer deleteFlag;


}

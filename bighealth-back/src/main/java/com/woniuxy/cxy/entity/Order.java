package com.woniuxy.cxy.entity;

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
  @TableName("h_order")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private String orderNumber;

    private BigDecimal amount;

    private Date createTime;

    private String userAccount;

    private String orderSource;

    private String paymentMethod;

    private Long addressId;

    private Integer state;

    private Date updateTime;

    private Integer deleteFlag;


}

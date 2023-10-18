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
  @TableName("h_item")
@ApiModel(value = "Item对象", description = "")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Long commodityId;

    private String commodityName;

    private BigDecimal price;

    private Integer bcount;

    private BigDecimal sumprice;

    private Long orderId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Integer deleteFlag;


}

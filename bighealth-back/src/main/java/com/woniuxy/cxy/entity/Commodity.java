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
  @TableName("h_commodity")
@ApiModel(value = "Commodity对象", description = "")
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private String name;

    private Long categoryId;

    private Integer subcategoryId;

    private String img;

    private String title;

    private Integer weight;

    private String subhead;

    private Integer sequence;

    private String code;

    private Integer stock;

    private Integer stockAlert;

    private BigDecimal price;

    private String details;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleteFlag;


}

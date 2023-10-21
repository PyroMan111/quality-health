package com.woniuxy.cxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
  @TableName("h_freight")
@ApiModel(value = "Freight对象", description = "")
public class Freight implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private String logisticsCompany;

    private String shippingTo;

    private Integer lightweight;

    private Integer middleweight;

    private Integer heavyweight;

    private Integer weightGainCost;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer deleteFlag;


}

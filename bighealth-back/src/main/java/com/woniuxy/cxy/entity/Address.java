package com.woniuxy.cxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
  @TableName("h_address")
@ApiModel(value = "Address对象", description = "")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private String province;

    private String city;

    private String area;

    private String detailAddress;

    private String tel;

    private String reciver;

    private String status;

    private String isDefault;

    private Long userId;

    private Date createTime;

    private Date updateTime;

    private Integer deleteFlag;


}

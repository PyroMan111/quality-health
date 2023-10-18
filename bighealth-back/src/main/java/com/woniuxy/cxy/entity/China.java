package com.woniuxy.cxy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
  @TableName("h_china")
@ApiModel(value = "China对象", description = "")
public class China implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String name;

    private Integer pid;


}

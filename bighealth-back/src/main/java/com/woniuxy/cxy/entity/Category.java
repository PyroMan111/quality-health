package com.woniuxy.cxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@TableName("h_category")
@ApiModel(value = "Category对象", description = "")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String categoryName;

    private Integer sequence;

    private String parentCategoryId;

    private String description;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @TableLogic
    private Integer deleteFlag;

//    @Override
//    public String toString() {
//        return "Category{" +
//                "objectMapper=" + objectMapper +
//                ", id=" + id +
//                ", categoryName='" + categoryName + '\'' +
//                ", sequence=" + sequence +
//                ", parentCategoryId='" + parentCategoryId + '\'' +
//                ", description='" + description + '\'' +
//                ", createTime=" + createTime +
//                ", updateTime=" + updateTime +
//                ", deleteFlag=" + deleteFlag +
//                '}';
//    }
}

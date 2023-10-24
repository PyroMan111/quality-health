package com.woniuxy.cxy.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@TableName("h_commodity")
@ApiModel(value = "CommodityDto对象", description = "")
@Document(indexName = "commodityDto_list")
public class CommodityDto implements Serializable {

    private static final long serialVersionUID = 1L;

    // 指定es中文件的主键id
    @Id
    private Long id;
    // 指定es的分词器与字段类型(json的key的类型)
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Integer)
    private Long categoryId;

    @Field(type = FieldType.Integer)
    private Integer subcategoryId;
    @Field
    private String img;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Integer)
    private Integer weight;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String subhead;

    @Field(type = FieldType.Integer)
    private Integer sequence;
    @Field(type = FieldType.Keyword)
    private String code;
    @Field(type = FieldType.Integer)
    private Integer stock;
    @Field(type = FieldType.Integer)
    private Integer stockAlert;
    @Field(type = FieldType.Double)
    private BigDecimal price;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String details;
    @Field(type = FieldType.Integer)
    private Integer status;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @Field(type = FieldType.Integer)
    @TableLogic
    private Integer deleteFlag;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String categoryName;

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", subcategoryId=" + subcategoryId +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", weight=" + weight +
                ", subhead='" + subhead + '\'' +
                ", sequence=" + sequence +
                ", code='" + code + '\'' +
                ", stock=" + stock +
                ", stockAlert=" + stockAlert +
                ", price=" + price +
                ", details='" + details + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}

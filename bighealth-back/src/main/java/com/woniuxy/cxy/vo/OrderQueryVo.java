package com.woniuxy.cxy.vo;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryVo {
    private String orderNumber;
    private Long userId;

    private Integer state;
    private Long orderSource;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private Integer pageNum;
    private Integer pageSize;

}

package com.woniuxy.cxy.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class PermissonMenuVo {
    private Long id;
    private String name;
    private String url;
    private List<PermissonMenuVo> children;
}
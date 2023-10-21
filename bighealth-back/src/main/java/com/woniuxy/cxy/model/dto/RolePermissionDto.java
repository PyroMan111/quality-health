package com.woniuxy.cxy.model.dto;

import lombok.Data;

@Data
public class RolePermissionDto {
    private Long roleId;
    private Long[] permissionIds;
}
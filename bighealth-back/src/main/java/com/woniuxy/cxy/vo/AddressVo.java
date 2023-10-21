package com.woniuxy.cxy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVo {
   private Long id;
   private String address;
   private String isDefault;


}

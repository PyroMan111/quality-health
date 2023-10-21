package com.woniuxy.cxy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {
    private Long id;//登录用户id
    private String currentUser;//登录用户的名称
    private String accessToken;// 短token：访问token
    private String freshTokern;// 长token：刷新短token的作用

//    public static void main(String[] args) {
//        TokenVo tokenVo = new TokenVo(1L, "", "", "");
//        TokenVo tokenVo1 = TokenVo.builder() // 构造者模式； 支持方法连缀写法
//                .id(1L)
//                .accessToken("")
//                .freshTokern("")
//                .build();
//    }

}

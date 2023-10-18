package com.woniuxy.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
//修改url username password
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/bighealth","root", "root")
                .globalConfig(builder -> {
                    builder
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .dateType(DateType.ONLY_DATE).disableOpenDir()
                            .outputDir("D:\\Documents\\p3\\w5\\d2-start\\backend\\J05-p3-bighealth\\J05-p3-bighealth\\bighealth-back\\src\\main\\java");
                    // 指定输出目录
//                                      D:\IDEA\IdeaProject\p3-w1-first\p3-w1\D3-w5-health-sys\h-portal\src\main\java
                }).packageConfig(builder -> {
                    builder.parent("com.woniuxy.cxy") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                "D:\\Documents\\p3\\w5\\d2-start\\backend\\J05-p3-bighealth\\J05-p3-bighealth\\bighealth-back\\src\\main\\resources\\mapper"));
                    // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableHyphenStyle().enableRestStyle();
                    builder.addInclude("h_address", "h_aftermarket", "h_category",
                                    "h_commodity", "h_freight","h_item",
                                    "h_order","h_china")
// 设置需要生成的表名
                            .addTablePrefix("h_"); // 设置过滤表前缀
                })
                .execute();
    }
}

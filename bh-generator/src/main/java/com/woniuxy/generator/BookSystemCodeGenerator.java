package com.woniuxy.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.util.Collections;

public class BookSystemCodeGenerator {
    public static void main(String[] args) {
//修改url username password
        FastAutoGenerator.create("jdbc:mysql://192.168.80.9:3306/woniubook",
                        "root", "root")
                .globalConfig(builder -> {
                    builder
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .dateType(DateType.ONLY_DATE).disableOpenDir()
                            .outputDir("D:\\IDEA\\IdeaProject\\p3-w1-first\\p3-w1\\D5-w1-book-sys\\book-system\\src\\main\\java"); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent("com.woniuxy.system") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    "D:\\IDEA\\IdeaProject\\p3-w1-first\\p3-w1\\D5-w1-book-sys\\book-system\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableHyphenStyle().enableRestStyle();
                    builder.addInclude(
                                    "t_manager","t_manager_role","t_role",
                                    "t_role_url_permission","t_url_permission")
// 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .execute();
    }
}

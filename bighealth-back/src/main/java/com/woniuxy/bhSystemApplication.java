package com.woniuxy;

//import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class bhSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(bhSystemApplication.class, args);
    }

}

package com.woniuxy.cxy.config;

import com.woniuxy.cxy.service.IChinaService;
import com.woniuxy.cxy.entity.China;
import com.woniuxy.cxy.redisConstant.RedisConstant;
import com.woniuxy.cxy.service.IChinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitChinaAddressRunner implements CommandLineRunner {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IChinaService chinaService;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("我来测试一下，看看究竟有没有被正确配置Running CommandLineRunner...");

// 判断：缓存中没有数据才去查询
        if (!redisTemplate.hasKey(RedisConstant.CHINA_CACHE)) {
            List<China> list = chinaService.list();
            list.forEach(china -> {
                redisTemplate.opsForHash().put(
                        RedisConstant.CHINA_CACHE, china.getId().toString(),
                        china.getName());
            });
        }
    }
}
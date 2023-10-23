package com.woniuxy.cxy.config;

import com.woniuxy.cxy.entity.Category;
import com.woniuxy.cxy.entity.Commodity;
import com.woniuxy.cxy.redisConstant.RedisConstant;
import com.woniuxy.cxy.repository.CommodityRepository;
import com.woniuxy.cxy.service.ICategoryService;
import com.woniuxy.cxy.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 在有加入Redis缓存后：
 * 依然存在的问题：项目一上线，瞬间有10W个请求同时访问首页，redis中没有，就查询了数据库，直
 * 接造成系统崩溃
 * <p>
 * 缓存击穿：redis没有，数据库有。 通过数据预热解决
 * 解决方案：数据预热
 */

/**
 * CommandLineRunner
 * 1、Spring Boot提供了CommandLineRunner接口，您可以使用他完成项目启动时候执行一些初始化操
 * 作。
 * 2、加载配置、初始化数据库连接、预加载数据（数据预热）
 */
@Component
public class InitTop5CommodityRunner implements CommandLineRunner {
    //
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ICommodityService commodityService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private CommodityRepository commodityRepository;


    @Override
    public void run(String... args) throws Exception {
        // 查询所有商品
        List<Commodity> commodityList = commodityService.list();

        // 数据同步到es的索引库

//        elasticsearchRestTemplate.save(commodityList);
        commodityRepository.saveAll(commodityList);

        // 遍历商品，加入到Redis的zset集合，Sequence作为展示分值
        commodityList.forEach(commodity -> {
            redisTemplate.opsForZSet().add(RedisConstant.COMMODITY_LIST, commodity, commodity.getSequence());

            redisTemplate.opsForHash().put("stock", commodity.getId().toString(), commodity.getStock());

        });

////        /**将所有品类写入redis缓存*/
//////        查询所有品类
//        List<Category> categoryList = categoryService.list();
//        categoryList.forEach(category -> {
//            redisTemplate.opsForList().leftPush("categories:" + category.getId(), category);
//        });

    }

//    @Override
//    public void run(String... args) throws Exception {
//        //        查询所有商品
//        List<Commodity> commodityList = commodityService.list();
//
//
////        elasticsearchRestTemplate.save(commodityList);
//        commodityRepository.saveAll(commodityList);
//
//
////        遍历商品，加入到Redis的zset集合，Sequence作为优先级次序，越小越靠前，即降序
//        commodityList.forEach(commodity -> {
//            redisTemplate.opsForZSet().add(
//                    RedisConstant.COMMODITY_LIST, commodity, commodity.getSequence());
//
//
////            redisTemplate.opsForHash().put("storage", commodity.getId().toString(), commodity.getStorecount());
//        });
//    }


}

package com.woniuxy.cxy.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.entity.Commodity;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.redisConstant.RedisConstant;
import com.woniuxy.cxy.service.ICategoryService;
import com.woniuxy.cxy.service.ICommodityService;
import com.woniuxy.cxy.vo.CommodityAdvancedQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@CrossOrigin
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    ICommodityService commodityService;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 首页查询商品Top5
     * 改造：从Redis中获取zset集合中数据，按照分值升获取。
     */
    @Cacheable(cacheNames = "top5List")
    @GetMapping("/top5List")
    public Result top5List() {
        // 参数1：zset的key；参数2：分值的范围（开始）；参数3：分值的范围（结束）
        // 参数4：获取元素的起始位置； 参数5：返回的行数
        Set<ZSetOperations.TypedTuple> datas =
                redisTemplate.opsForZSet().rangeByScoreWithScores(
                        RedisConstant.COMMODITY_LIST, 0, Double.MAX_VALUE, 0, 5);
        // 处理结果: 把zset返回的数据，转换为List<Commodity>
        List<Commodity> commodityList = datas.stream().map(data -> {
            // 把Map数据转换为json字符串
            String jsonString = JSONUtil.toJsonStr(data.getValue());
            // 把json字符串转换为Commodity对象返回
            return JSONUtil.toBean(jsonString, Commodity.class);
        }).collect(Collectors.toList());
        return Result.ok(commodityList);
    }


    @Cacheable(cacheNames = "historical_advanced_query")
    @PostMapping("AdvancedQuery")
    public Result findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "5") Integer pageSize, @RequestBody CommodityAdvancedQueryVo advancedQuery) {
        PageVo page = commodityService.AdvancedQuery(pageNum,
                pageSize, advancedQuery);
        return Result.ok(page);
    }


    @GetMapping("/findAll")
    @Cacheable(cacheNames = "full_commodity_list")
    public Result findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "5") Integer pageSize) {
        PageVo page = commodityService.findAll(pageNum, pageSize);
        return Result.ok(page);
    }

    /**
     * 商品ID，商品名称，商品分类，商品图片，商品标题，商品编码，商品价格，商品库存，商品状态，操作
     * */

    /**
     * 商品名模糊查询
     */
    @GetMapping("/findAllCommodityByName")
    @Cacheable(cacheNames = "commodity_list")
    public Result findAll(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam String name) {
        PageVo pageVo = commodityService.findAllCommodityByName(pageNum, pageSize, name);
        return Result.ok(pageVo);
    }


    /**逻辑下架一件商品*/


    /**上架（添加）一件商品*/

//    /**全文检索*/
//    @GetMapping("/toSearch")
//    public String toSearch(String keyword, Model model) {
//        model.addAttribute("keyword", keyword);
//        return "search";
//    }


    /**
     * 首页搜索
     */
    @GetMapping("/search")
    @Cacheable(cacheNames = "searched_keywords")
    public Result search(
            String keyword,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
// 调用service查询索引库
        Map<String, Object> result = commodityService.search(keyword, pageNum, pageSize);
        return Result.ok(result);
    }

}

package com.woniuxy.cxy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.entity.Commodity;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.service.ICategoryService;
import com.woniuxy.cxy.service.ICommodityService;
import com.woniuxy.cxy.vo.CommodityAdvancedQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    ICommodityService commodityService;


    @RequestMapping("AdvancedQuery")
    public Result findAll(@RequestBody CommodityAdvancedQueryVo advancedQuery) {
        List<Commodity> list = commodityService.AdvancedQuery(advancedQuery);
        return Result.ok(list);

    }

    @GetMapping("/findAll")
    public Result findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "5") Integer pageSize) {
        PageVo page = commodityService.findAll(pageNum, pageSize);
        return Result.ok(page);
    }
    /**
     * 商品ID，商品名称，商品分类，商品图片，商品标题，商品编码，商品价格，商品库存，商品状态，操作
     * */

    /**商品名模糊查询*/
    @GetMapping("/findAllCommodityByName")
    public Result findAll(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam String name) {
        PageVo pageVo = commodityService.findAllCommodityByName(pageNum, pageSize, name);
        return Result.ok(pageVo);
    }


    /**逻辑下架一件商品*/


    /**上架（添加）一件商品*/

    /**全文检索*/
    @GetMapping("/toSearch")
    public String toSearch(String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "search";
    }


    /**
     * 首页搜索
     */
    @GetMapping("/search")
    public Result search(
            String keyword,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
// 调用service查询索引库
        Map<String, Object> result = commodityService.search(keyword, pageNum, pageSize);
        return Result.ok(result);
    }

}

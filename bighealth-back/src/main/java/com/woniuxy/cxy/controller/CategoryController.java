package com.woniuxy.cxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.entity.Category;
import com.woniuxy.cxy.service.ICategoryService;
import com.woniuxy.cxy.vo.CategoryQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

//        @PostMapping("/search")
//    public Result list(@RequestBody CategoryQueryVo categoryQueryVo, Integer pageNum, Integer pageSize) {
//        Map<String, Object> condition = new HashMap<>();
//
//        condition.put("parentCategoryId", categoryQueryVo.getParentCategoryId());
//
//        condition.put("beginDate", categoryQueryVo.getBeginDate());
//
//        condition.put("endDate", categoryQueryVo.getEndDate());
//
//        IPage<Category> page = categoryService.search(condition, pageNum, pageSize);
//
//        return Result.ok(page);
//    }
    @RequestMapping("findByCondition")
    public PageInfo<Category> findByCondition(@RequestBody Map<String, Object> condition) {
        Integer pageNum = (Integer) condition.get("page");
        Integer pageSize = (Integer) condition.get("size");

        System.out.println("查询条件：" + condition);
        PageInfo<Category> pageInfo =  categoryService.findByCondition(condition, pageNum, pageSize);
        return pageInfo;
    }


    /**
     * 查询所有商品类别
     */
    @GetMapping("/findAll")
    public Result<IPage> findCategories(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize) {
//        List<Category> list = categoryService.getBaseMapper().selectList(null);
//        List<Category> list = categoryService.findAll();
        Page page = categoryService.findAll(pageNum, pageSize);
        return Result.ok(page);
//        return null;
    }


    /**
     * 新增
     */
    @PostMapping("/add")
    @CacheEvict(cacheNames = "saved_categories",allEntries = true)
    public Result saveOrUpdate(@RequestBody Category category) {

        return Result.ok(categoryService.saveOrUpdate(category));
    }
    /**添加或修改*/


    /**
     * 查询所有品类（id和类名）
     */
    @GetMapping("/findAllCategoryName")
//    @Cacheable(cacheNames = "categoryName_list")
    @Cacheable(cacheNames = "categoryName_list")
    public Result findAllCategory() {
        List list = categoryService.findCategoryNames();
        return Result.ok(list);
    }




}


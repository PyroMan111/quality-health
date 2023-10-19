package com.woniuxy.cxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.entity.Category;
import com.woniuxy.cxy.service.ICategoryService;
import com.woniuxy.cxy.vo.CategoryQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/search")
    public Result list(@RequestBody CategoryQueryVo categoryQueryVo,Integer pageNum, Integer pageSize) {
        Map<String, Object> condition = new HashMap<>();

        condition.put("parentCategoryId", categoryQueryVo.getParentCategoryId());

        condition.put("beginDate", categoryQueryVo.getBeginDate());

        condition.put("endDate", categoryQueryVo.getEndDate());

        IPage<Category> page = categoryService.search(condition, pageNum, pageSize);

        return Result.ok(page);
    }


    /**
     * 新增
     */
    @PostMapping("/add")
    public Result save(@RequestBody Category category) {

        return null;
    }



}


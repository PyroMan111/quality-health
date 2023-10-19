package com.woniuxy.cxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cxy.entity.Category;
import com.woniuxy.cxy.mapper.CategoryMapper;
import com.woniuxy.cxy.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
//import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CategoryMapper categoryMapper;

    //    @Cacheable(cacheNames = "categories", key = " '_' + #pageNum + '_' + #pageSize")
    @Override
    public IPage<Category> search(Map<String, Object> condition, Integer pageNum, Integer pageSize) {
        // 构造LambdaQueryWrapper
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();

        // 添加查询条件 - 使用Optional包装
        Optional.ofNullable(condition.get("parentCategoryId")).ifPresent(id -> queryWrapper.eq(Category::getParentCategoryId, id));
        Optional.ofNullable(condition.get("beginDate")).ifPresent(date -> queryWrapper.ge(Category::getCreateTime, date));
        Optional.ofNullable(condition.get("endDate")).ifPresent(date -> queryWrapper.le(Category::getCreateTime, date));

        // 进行分页查询
        IPage<Category> page = categoryMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return page;
    }





}

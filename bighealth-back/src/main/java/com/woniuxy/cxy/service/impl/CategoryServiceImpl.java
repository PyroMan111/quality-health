package com.woniuxy.cxy.service.impl;

import com.woniuxy.cxy.entity.Category;
import com.woniuxy.cxy.mapper.CategoryMapper;
import com.woniuxy.cxy.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
//    @Autowired
//    private RedisTemplate redisTemplate;


}

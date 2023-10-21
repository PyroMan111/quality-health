package com.woniuxy.cxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.cxy.entity.Category;
import com.woniuxy.cxy.mapper.CategoryMapper;
import com.woniuxy.cxy.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
//    @Override
//    public IPage<Category> search(Map<String, Object> condition, Integer pageNum, Integer pageSize) {
//        // 构造LambdaQueryWrapper
//        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
//        // 添加查询条件 - 使用Optional包装
////        Optional.ofNullable(condition.get("categoryName")).ifPresent(id -> queryWrapper.eq(Category::getCategoryName, ));
//        Optional.ofNullable(condition.get("categoryName")).ifPresent(name -> queryWrapper.like(Category::getCategoryName, name));
//        Optional.ofNullable(condition.get("begin")).ifPresent(date -> queryWrapper.ge(Category::getCreateTime, date));
//        Optional.ofNullable(condition.get("end")).ifPresent(date -> queryWrapper.le(Category::getCreateTime, date));
//        // 检查所有条件是否都为空
//        if (condition.isEmpty()) {
//            // 在这里处理所有条件为空的情况，比如返回一个特殊的值或者抛出一个异常
//            // 这里只是简单地返回一个空的IPage作为示例
//            List<Category> list = categoryMapper.selectList(null);
//            Page page = new Page().setRecords(list);
//            return page;
//        }
//        // 进行分页查询
//        IPage<Category> page = categoryMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
//        return page;
//    }

    @Override
    public PageInfo<Category> findByCondition(Map<String, Object> condition, int pageNum, int pageSize) {
        //1.设置页码和每页大小
        PageHelper.startPage(pageNum, pageSize);

//        int page = (int) condition.get("page");
//        int size = (int) condition.get("size");
        System.out.println("condition = " + condition);
        System.out.println(condition.get("categoryName"));

        List<Category> date = categoryMapper.QueryCategoryByCondition(condition);
//        int count = operationLogMapper.count(condition);
        PageInfo<Category> pageInfo = new PageInfo<>(date);

        return pageInfo;
    }

//    @Override
//    public Page<Category> findAll(Integer pageNum, Integer pageSize){
//
//        return null;
//    }

    @Override
    public Page<Category> findAll(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Category> wrapper = Wrappers.lambdaQuery(Category.class);
        // 在这里可以根据需要添加查询条件，比如wrapper.like(StringUtils.hasText(name), Category::getName, name);

        Page<Category> page = baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return page;
    }

//    @Override
//    public List findAllCategoryName() {
////        只查id和类名
//        List list = categoryMapper.findAllCategoryName();
//        return list;
//    }

    public List<Category> findAllCategoryName() {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Category::getId, Category::getCategoryName);

        return categoryMapper.selectList(wrapper);

    }


}

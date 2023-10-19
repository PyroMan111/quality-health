package com.woniuxy.cxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniuxy.cxy.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
public interface ICategoryService extends IService<Category> {
    //    @Cacheable(cacheNames = "categories", key = " '_' + #pageNum + '_' + #pageSize")
//    IPage<Category> search(String parentCategoryId, List<LocalDateTime> dateArrays, Integer pageNum, Integer pageSize);

    //    @Cacheable(cacheNames = "categories", key = " '_' + #pageNum + '_' + #pageSize")
    IPage<Category> search(Map<String, Object> condition, Integer pageNum, Integer pageSize);

//    @Cacheable(cacheNames = "categories", key = " '_' + #pageNum + '_' + #pageSize")
//        //    public IPage<Category> search(CategoryQueryVo categoryQueryVo, Integer pageNum, Integer pageSize) {
//    IPage<Category> search(String parentCategoryId, List<Date> dateArrays, Integer pageNum, Integer pageSize);
}

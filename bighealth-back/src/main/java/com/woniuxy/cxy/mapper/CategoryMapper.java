package com.woniuxy.cxy.mapper;

import com.woniuxy.cxy.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.lucene.search.LeafSimScorer;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> QueryCategoryByCondition (@Param("condition") Map<String, Object> condition);



}

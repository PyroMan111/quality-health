package com.woniuxy.cxy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cxy.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.vo.CommodityAdvancedQueryVo;

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
public interface ICommodityService extends IService<Commodity> {

    List<Commodity> top10List();


    PageVo<Commodity> findAll(Integer pageNum, Integer pageSize);


    PageVo<Commodity> AdvancedQuery(Integer pageNum, Integer pageSize, CommodityAdvancedQueryVo advancedQueryVo);

    PageVo<Commodity> findAllCommodityByName(Integer pageNum, Integer pageSize, String name);

    Map<String, Object> search(String keyword, Integer pageNum, Integer pageSize);
}

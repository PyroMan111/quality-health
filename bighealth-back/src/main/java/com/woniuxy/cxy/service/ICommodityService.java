package com.woniuxy.cxy.service;

import com.woniuxy.cxy.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
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

    List<Commodity> AdvancedQuery(CommodityAdvancedQueryVo advancedQueryVo);
}

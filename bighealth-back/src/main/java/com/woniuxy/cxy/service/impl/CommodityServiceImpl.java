package com.woniuxy.cxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cxy.entity.Commodity;
import com.woniuxy.cxy.mapper.CommodityMapper;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.service.ICommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.cxy.vo.CommodityAdvancedQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements ICommodityService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private CommodityMapper commodityMapper;


    @Override
    public List<Commodity> top10List() {
        // 参数1：分页对象
        Page<Commodity> page = Page.of(1, 10);
        // 参数2：构建查询对象进行排序
        // SELECT id,NAME,price,imgsrc FROM t_book ORDER BY buycount DESC LIMIT 10
        LambdaQueryWrapper<Commodity> wrapper = Wrappers.lambdaQuery(Commodity.class);
        // 查询指定的列, 默认是 select * , 开发中要避免这样用，效率低。
        wrapper.select(Commodity::getId, Commodity::getName, Commodity::getPrice, Commodity::getImg);
        // 排序
//        按sequence排序
        wrapper.orderByDesc(Commodity::getSequence);
        Page<Commodity> pageData = this.page(page, wrapper);
        // 返回分页数据
        return pageData.getRecords();
    }

    @Override
    public PageVo<Commodity> findAll(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Commodity> wrapper = Wrappers.lambdaQuery(Commodity.class);
        Page<Commodity> page = baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return new PageVo(page.getRecords(), page.getTotal());
    }

    @Override
    public List<Commodity> AdvancedQuery(CommodityAdvancedQueryVo advancedQueryVo){
        LambdaQueryWrapper<Commodity> wrapper = new LambdaQueryWrapper<>();


        // 设置查询条件  
        if (advancedQueryVo.getName() != null) {
            wrapper.like(Commodity::getName, advancedQueryVo.getName());

        }
        if (advancedQueryVo.getCategoryId() != null) {
            wrapper.eq(Commodity::getCategoryId, advancedQueryVo.getCategoryId());
        }
        if (advancedQueryVo.getStatus() != null) {
            wrapper.eq(Commodity::getStatus, advancedQueryVo.getStatus());
        }
        if (advancedQueryVo.getCode() != null) {
            wrapper.like(Commodity::getCode, advancedQueryVo.getCode());
        }
        if (advancedQueryVo.getLowPrice() != null) {
            wrapper.ge(Commodity::getPrice, advancedQueryVo.getLowPrice().doubleValue());
        }
        if (advancedQueryVo.getHighPrice() != null) {
            wrapper.le(Commodity::getPrice, advancedQueryVo.getHighPrice().doubleValue());
        }

        return commodityMapper.selectList(wrapper);
    }

    @Override
    public PageVo<Commodity> findAllCommodityByName(Integer pageNum, Integer pageSize, String name) {
        LambdaQueryWrapper<Commodity> wrapper = Wrappers.lambdaQuery(Commodity.class);
        // 在这里可以根据需要添加查询条件，比如wrapper.like(StringUtils.hasText(name), Commodity::getName, name);
        wrapper.like(Commodity::getName, name);

        Page<Commodity> page = baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return new PageVo(page.getRecords(), page.getTotal());
    }

}

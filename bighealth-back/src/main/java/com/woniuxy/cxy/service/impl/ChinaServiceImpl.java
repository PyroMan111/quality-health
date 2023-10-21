package com.woniuxy.cxy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.woniuxy.cxy.entity.China;
import com.woniuxy.cxy.mapper.ChinaMapper;
import com.woniuxy.cxy.service.IChinaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Service
public class ChinaServiceImpl extends ServiceImpl<ChinaMapper, China> implements IChinaService {

    @Cacheable(cacheNames = "CITIES_CACHE", key = "#root.methodName + #proviceId")
    @Override
    public List<China> getAllCities(Integer proviceId) {
        return baseMapper.selectList(Wrappers.lambdaQuery(China.class)
                .eq(Objects.nonNull(proviceId), China::getPid, proviceId));
    }

    @Cacheable(cacheNames = "AREAS_CACHE", key = "#root.methodName + #cityId")
    @Override
    public List<China> getAllAreas(Integer cityId) {
        return baseMapper.selectList(Wrappers.lambdaQuery(China.class)
                .eq(Objects.nonNull(cityId), China::getPid, cityId));
    }
}

package com.woniuxy.cxy.service;

import com.woniuxy.cxy.entity.China;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
public interface IChinaService extends IService<China> {

    List<China> getAllCities(Integer proviceId);

    List<China> getAllAreas(Integer cityId);
}

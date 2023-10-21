package com.woniuxy.cxy.service;

import com.woniuxy.cxy.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.cxy.vo.AddressVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
public interface IAddressService extends IService<Address> {

    List<Address> findAddressByUserId(int userId);

    List<AddressVo> findConsigneeAddress();
}

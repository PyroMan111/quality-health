package com.woniuxy.cxy.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.woniuxy.cxy.entity.Address;
import com.woniuxy.cxy.mapper.AddressMapper;
import com.woniuxy.cxy.redisConstant.RedisConstant;
import com.woniuxy.cxy.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.cxy.vo.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    QueryWrapper wrapper;

    @Override
    public List<Address> findAddressByUserId(int userId) {
        return this.list(Wrappers.lambdaQuery(Address.class)
                .eq(Objects.nonNull(userId), Address::getUserId,userId));
    }

    @Override
    public List<AddressVo> findConsigneeAddress() {

//        判断：若redis中无此key，则查数据库
        if (!redisTemplate.hasKey(RedisConstant.USER_FULL_ADDRESS)) {

            //查询数据库
            List<Address> list = baseMapper.selectList(null);


            List<AddressVo> result = list.stream().map(address ->
                    new AddressVo(address.getId(),
                            address.getProvince() + address.getCity() + address.getArea() + address.getDetailAddress(),
                            address.getIsDefault())).collect(Collectors.toList());
//            查询结果放入redis
            redisTemplate.opsForList().leftPushAll(RedisConstant.USER_FULL_ADDRESS, result);
            return result;
        }
//        从redis中获取数据
//        List resultList = redisTemplate.opsForList().range(RedisConstant.BOOK_TYPE_LIST, 0, -1);
        List resultList = redisTemplate.opsForList().range(RedisConstant.USER_FULL_ADDRESS, 0, -1);

        return resultList;
    }
}

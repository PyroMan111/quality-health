package com.woniuxy.cxy.service.impl;

import com.woniuxy.cxy.entity.Order;
import com.woniuxy.cxy.mapper.*;
import com.woniuxy.cxy.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


//import com.woniuxy.cxy.entity.Book;
//import com.woniuxy.cxy.mapper.BookMapper;
import com.woniuxy.cxy.mapper.ItemMapper;
import com.woniuxy.cxy.mapper.OrderMapper;
import com.woniuxy.cxy.vo.CartVo;
import org.redisson.api.RedissonClient;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    @Transactional
    public void submitOrder(Integer userId, Integer addressId, CartVo cartVo, String orderNum) {

    }

}

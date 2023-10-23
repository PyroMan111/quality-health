package com.woniuxy.cxy.service.impl;

import com.woniuxy.cxy.entity.Order;
import com.woniuxy.cxy.mapper.*;
import com.woniuxy.cxy.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.cxy.common.constant.OrderStatusEnum;
import com.woniuxy.cxy.common.enums.impl.BusinessCode;
import com.woniuxy.cxy.common.exception.Asserts;
import com.woniuxy.cxy.common.exception.BusinessException;
import com.woniuxy.cxy.entity.Address;
//import com.woniuxy.cxy.entity.Book;
import com.woniuxy.cxy.entity.Item;
import com.woniuxy.cxy.entity.Order;
//import com.woniuxy.cxy.mapper.BookMapper;
import com.woniuxy.cxy.mapper.ItemMapper;
import com.woniuxy.cxy.mapper.OrderMapper;
import com.woniuxy.cxy.service.IOrderService;
import com.woniuxy.cxy.vo.CartItemVo;
import com.woniuxy.cxy.vo.CartVo;
import com.woniuxy.cxy.vo.OrderItemVO;
import com.woniuxy.cxy.vo.OrderVO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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

}

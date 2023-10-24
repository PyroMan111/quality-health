package com.woniuxy.cxy.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cxy.entity.Commodity;
import com.woniuxy.cxy.entity.Item;
import com.woniuxy.cxy.entity.Order;
import com.woniuxy.cxy.mapper.*;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.cxy.vo.OrderQueryVo;
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

import java.util.Date;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
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


    /**
     * 条件查询及分页
     * 根據订单编号模糊查询、用户账号模糊查询、精确订单状态查询
     * SELECT * FROM h_order  WHERE order_number like '%3665%' and user_id like '%3%' and state = '1'
     */

    @Override
    public PageVo<Order> AdvancedQuery(Integer pageNum, Integer pageSize, OrderQueryVo orderQueryVo) {
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery(Order.class);
        wrapper
                .like(orderQueryVo.getOrderNumber() != null,
                        Order::getOrderNumber, orderQueryVo.getOrderNumber())

                .like(orderQueryVo.getUserId() != null,
                        Order::getUserId, orderQueryVo.getUserId())

                .eq(orderQueryVo.getState() != null,
                        Order::getState, orderQueryVo.getState())

                .eq(orderQueryVo.getOrderSource() != null,
                        Order::getOrderSource, orderQueryVo.getOrderSource())

                .ge(orderQueryVo.getStartDate() != null,
                        Order::getCreateTime, orderQueryVo.getStartDate())

                .le(orderQueryVo.getEndDate() != null,
                        Order::getCreateTime, orderQueryVo.getEndDate());

        Page<Order> page = baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return new PageVo(page.getRecords(), page.getTotal());
    }


//    @Override
//    @Transactional
//    public void submitOrder(Integer userId, Integer addressId, CartVo cartVo, String orderNum) {
//        // 生产订单ID: 雪花算法生成
//        // 参数1 机器id； 参数2：机房id
//        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
//        long orderId = snowflake.nextId();
//
//        // 订单明细
//        Item item = new Item();
//        item.setBookId(cartItemVo.getId());
//        item.setBookName(cartItemVo.getName());
//        item.setPrice(cartItemVo.getPrice());
//        item.setBcount(cartItemVo.getBuycount());
//        item.setSumprice(cartItemVo.getSumPrice());
//        item.setOrderId(orderId); // 引用订单ID
//        item.setCreatetime(new Date());
//        itemMapper.insert(item);
//
//    }

}

package com.woniuxy.cxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cxy.entity.Commodity;
import com.woniuxy.cxy.entity.Item;
import com.woniuxy.cxy.entity.Order;
import com.woniuxy.cxy.mapper.*;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.cxy.vo.OrderItemVo;
import com.woniuxy.cxy.vo.OrderQueryVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


//import com.woniuxy.cxy.entity.Book;
//import com.woniuxy.cxy.mapper.BookMapper;
import com.woniuxy.cxy.mapper.ItemMapper;
import com.woniuxy.cxy.mapper.OrderMapper;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.List;

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
     * 根据订单编号模糊查询、用户账号模糊查询、精确订单状态查询
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

    @Override
    public PageVo findAll(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery(Order.class);
        // 在这里可以根据需要添加查询条件，比如wrapper.like(StringUtils.hasText(name), Category::getName, name);

        Page<Order> page = baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return new PageVo(page.getRecords(), page.getTotal());
    }


//    订单是用户端处理了接受到的，下面要查看某笔订单里的OrderItem


    /**
     * 查看某笔订单有哪些OrderItem
     */
    @Override
    public List<OrderItemVo> findItemByOrderId(Long id) {
        //        确定此订单为非失效
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Order::getId, id)
                .ne(Order::getState, 0);

        Order order = orderMapper.selectOne(queryWrapper);
        if (order == null) {
            return null;
        }

        // 创建查询条件
        QueryWrapper<Item> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", id);


        // 执行查询
        List<Item> itemList = itemMapper.selectList(wrapper);

        // 转换结果为OrderItemVo对象列表
        List<OrderItemVo> orderItemVoList = new ArrayList<>();
        for (Item item : itemList) {
            OrderItemVo orderItemVo = new OrderItemVo();
            orderItemVo.setId(item.getId());
            orderItemVo.setName(item.getCommodityName());

            /**根据Item项找这个商品的图片*/

            orderItemVo.setImg(findImgByCommodityId(item.getCommodityId())); // TODO: 根据需要设置图片字段

            orderItemVo.setBcount(item.getBcount());
            orderItemVo.setSumprice(item.getSumprice());
            orderItemVoList.add(orderItemVo);
        }
        return orderItemVoList;
    }

    // 定义一个新的方法来获取商品图片
    @Override
    public String findImgByCommodityId(Long id) {

        // 创建查询条件

        QueryWrapper<Commodity> wrapper = new QueryWrapper<>();

        wrapper.eq("id", id);

        // 执行查询

        Commodity commodity = commodityMapper.selectOne(wrapper);

        // 返回商品图片，如果没有找到则返回空字符串

        return commodity != null ? commodity.getImg() : "";

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

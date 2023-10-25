package com.woniuxy.cxy.service;

import com.woniuxy.cxy.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.vo.CartVo;
import com.woniuxy.cxy.vo.OrderItemVo;
import com.woniuxy.cxy.vo.OrderQueryVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
public interface IOrderService extends IService<Order> {
    PageVo AdvancedQuery(Integer pageNum, Integer pageSize, OrderQueryVo orderQueryVo);

    PageVo findAll(Integer pageNum, Integer pageSize);


    List<OrderItemVo> findItemByOrderId(Long id);

    // 定义一个新的方法来获取商品图片
    String findImgByCommodityId(Long id);

//    @Transactional
//    void submitOrder(Integer userId, Integer addressId, CartVo cartVo, String orderNum);
}

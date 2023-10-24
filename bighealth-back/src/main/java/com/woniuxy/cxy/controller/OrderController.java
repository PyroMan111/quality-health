package com.woniuxy.cxy.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.entity.Order;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.service.IOrderService;
import com.woniuxy.cxy.vo.OrderQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    /**
     * 订单条件查询及分页
     * 根据订单编号模糊查询、用户账号模糊查询、精确订单状态查询
     * SELECT * FROM h_order  WHERE order_number like '%3665%' and user_id like '%3%' and state = '1'
     */
    @GetMapping("/findByCondition")
    public Result<PageVo> findByCondition(@RequestBody OrderQueryVo orderQueryVo) {
        PageVo page = orderService
                .AdvancedQuery(orderQueryVo.getPageNum(),orderQueryVo.getPageSize(), orderQueryVo);
        return Result.ok(page);
    }

}

package com.woniuxy.cxy.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.entity.Order;
import com.woniuxy.cxy.model.vo.PageVo;
import com.woniuxy.cxy.service.IOrderService;
import com.woniuxy.cxy.vo.OrderItemVo;
import com.woniuxy.cxy.vo.OrderQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    /**
     * 查询全部的分页
     * */
    @GetMapping("/findAll")
    @Cacheable(cacheNames = "full_commodity_list")
    public Result findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "5") Integer pageSize) {
        PageVo page = orderService.findAll(pageNum, pageSize);
        return Result.ok(page);
    }

    /**
     * 导出Excel
     */
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws IOException {
        //1.调用业务层获取要导出数据
        List<Order> list = orderService.getBaseMapper().selectList(null);

        //2.设置响应头，指定下载文件
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //文件名最好不使用汉字，否则需要进行编码，文件名会有乱码。告诉浏览器，这个文件要下载，并且指定文件名
        response.setHeader("content-disposition", "attachment;filename=background_order.xlsx");

        //3.调用EasyExcel的方法导出文件
        //获取响应输出流
        ServletOutputStream out = response.getOutputStream();
        //参数：输出流，实体类
        EasyExcel.write(out, Order.class)
                .sheet("订单列表")  //工作簿名字
                .doWrite(list);   //要写出的数据列表
    }

    /**订单详情
     * 此订单下有若干个OrderItem，应该有
     * 名称
     * 图片
     * 数量
     * 小计
     * 被传到前端
     * 传入一个orderId，返回一个List
     * */
    @GetMapping("/getItemByOrderId/{id}")
    public List<OrderItemVo> getItemByOrderId(@PathVariable Long id) {
        return orderService.findItemByOrderId(id);
    }



}

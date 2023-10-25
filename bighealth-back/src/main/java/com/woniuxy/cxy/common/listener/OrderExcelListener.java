
package com.woniuxy.cxy.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.woniuxy.cxy.entity.Order;
import com.woniuxy.cxy.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class OrderExcelListener extends AnalysisEventListener<Order> {
//    @Autowired
//    private IOrderService orderService;

    //通过构造方法传入业务对象
    private IOrderService orderService;

    public OrderExcelListener(IOrderService orderService) {
        this.orderService = orderService;
    }

    //创建一个集合保存所有导入的数据
    private List<Order> orderList = new ArrayList<>();

    //每条数据如何处理
    @Override
    public void invoke(Order order, AnalysisContext analysisContext) {
        //把每条数据添加到集合中
        orderList.add(order);
    }

    //数据导入完成后的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //写入到数据库中
//        rowCount = carInsuranceService.insertBatch(carInsuranceList);

        orderList.forEach(order -> {
            rowCount += orderService.getBaseMapper().insert(order);
        });
    }

    //添加的行数
    private int rowCount;

    //获取导入的行数
    public int getRowCount() {
        return this.rowCount;
    }
}

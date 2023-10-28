package com.woniuxy.cxy.controller;

import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.service.IAftermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@CrossOrigin
@RestController
@RequestMapping("/aftermarket")
public class AftermarketController {

    @Autowired
    IAftermarketService aftermarketService;

//    @GetMapping("page")
//    public Result getPage(Page page){
//
//    }


}

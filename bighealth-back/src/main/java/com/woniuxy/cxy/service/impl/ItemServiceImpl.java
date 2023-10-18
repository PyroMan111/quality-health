package com.woniuxy.cxy.service.impl;

import com.woniuxy.cxy.entity.Item;
import com.woniuxy.cxy.mapper.ItemMapper;
import com.woniuxy.cxy.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}

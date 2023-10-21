package com.woniuxy.cxy.controller;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.woniuxy.cxy.common.Result;
import com.woniuxy.cxy.entity.Address;
import com.woniuxy.cxy.entity.China;
import com.woniuxy.cxy.redisConstant.RedisConstant;
import com.woniuxy.cxy.service.IAddressService;
import com.woniuxy.cxy.service.IChinaService;
import com.woniuxy.cxy.vo.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-10-18
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IChinaService chinaService;

//    /**
//     * 查询所有地址
//     * */
//    @GetMapping
//    public Result<List<AddressVo>> findAll(){
//        List<AddressVo> list = addressservice.findConsigneeAddress();
//        return Result.ok(list);
//    }

    /**
     * 查询所有地址
     */
    @GetMapping("/findAll")
    public Result findAll(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        JWT jwt = JWTUtil.parseToken(token);
        Number userId = (Number) jwt.getPayload("userId");
// 从token中获取登录用户id，根据用户id查询地址
        List<Address> list = addressService.findAddressByUserId(userId.intValue());
// 转换： List<Address> 转换为 List<AddressVo>
        List<AddressVo> addressVos = list.stream().map(address -> parseAddress(address)).collect(Collectors.toList());
        return Result.ok(addressVos);
    }

    private AddressVo parseAddress(Address address) {
        return new AddressVo(address.getId(), address.getProvince() + address.getCity() + address.getArea() + address.getDetailAddress(), address.getIsDefault());
    }

    /**
     * 查询所有省份
     */
    @GetMapping("/getAllProvinces")
    public Result getAllProvinces() {
        List<China> list = null;
        // 1. 先判断Redis缓存中是否有省份的key
        if (!redisTemplate.hasKey(RedisConstant.PROVINCES)) {
            // 1.1 缓存中不存在，就查询数据库：SELECT * FROM t_china WHERE pid=0
            list = chinaService.list(Wrappers.lambdaQuery(China.class).ne(China::getId, 0).eq(China::getPid, 0));
            // 1.2. 存储到Redis中: 通过Redis的set集合存储
            list.forEach(china -> {
                redisTemplate.opsForSet().add(RedisConstant.PROVINCES, JSONUtil.toJsonStr(china));
            });
        } else {
            // 2. 直接从Redis中获取
            Set<String> datas = redisTemplate.opsForSet().members(RedisConstant.PROVINCES);
            list = datas.stream().map(str -> {
                // str 就是China对象的JSON字符串
                China china = JSONUtil.toBean(str, China.class);
                return china;
            }).collect(Collectors.toList());
        }
        return Result.ok(list);
    }

    /**
     * 根据省份查询所有的城市
     */
    @GetMapping("/getAllCities")
    public Result getAllCities(Integer proviceId) {
        List<China> list = chinaService.getAllCities(proviceId);
        return Result.ok(list);
    }

    /**
     * 根据城市加载区域
     */
    @GetMapping("/getAllAreas")
    public Result getAllAreas(Integer cityId) {
        List<China> list = chinaService.getAllAreas(cityId);
        return Result.ok(list);
    }

    /**
     * 保存地址
     */
    @PostMapping("/save")
    public Result save(@RequestBody Address address, @RequestHeader("Authorization") String
            authorization) {
        String token = authorization.replace("Bearer ", "");
        JWT jwt = JWTUtil.parseToken(token);
        Number userId = (Number) jwt.getPayload("userId");
        String province = (String)
                redisTemplate.opsForHash().get(RedisConstant.CHINA_CACHE, address.getProvince());
        String city = (String)
                redisTemplate.opsForHash().get(RedisConstant.CHINA_CACHE, address.getCity());
        String area = (String)
                redisTemplate.opsForHash().get(RedisConstant.CHINA_CACHE, address.getArea());
        address.setProvince(province);
        address.setCity(city);
        address.setArea(area);
        address.setStatus("1");
        address.setUserId(userId.longValue());
        // 如果客户端传入的地址isDefault=1, 说明是默认地址。 那么其他的属于这个用户的地址的全部修改为0
//

        if ("1".equals(address.getIsDefault())) {
            // 修改属于这个用户的、相同的userId，将其他的地址全部设置isDefault=0
            addressService.update(Wrappers.lambdaUpdate(Address.class)
                    .eq(Address::getUserId,userId.longValue())
                    .set(Address::getIsDefault, "0"));
        }
        System.out.println("address.getProvince()+address.getCity() = " + address.getProvince() + address.getCity());
        printFields(address);

        // 保存地址
        addressService.save(address);
        return Result.ok();
    }

    public static void printFields(Object obj) {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = null;
            try {
                fieldValue = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(fieldName + ": " + fieldValue);
        }
    }


}

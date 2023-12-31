package com.woniuxy.cxy.redisConstant;

public interface RedisConstant {

    //
    String CATEGORY_LIST = "category:list";
    String COMMODITY_LIST = "commodity:list";

    String USER_TOKEN_PREFIX = "user_token_";
    String USER_CART_PREFIX = "user_cart_";

    String USER_FULL_ADDRESS = "user_full_address:list";
    String PROVINCES = "province:list";
    String CHINA_CACHE = "china_placename_cache";
    String ORDER_IDEMPOTENT_PREFIX = "order_idempotent_";
}

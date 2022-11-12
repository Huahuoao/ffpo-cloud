package com.huahuo.utils.common;

import com.huahuo.feign.UserFeignService;
import com.huahuo.utils.thread.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @作者 花火
 * @创建日期 2022/11/8 20:52
 */
public class RedisUtils {
    @Autowired
    private static RedisTemplate redisTemplate;

    public static void clearStampCaChe(Integer id){
        Set<String> keys = redisTemplate.keys(id + "*");
        redisTemplate.delete(keys);
    }
}

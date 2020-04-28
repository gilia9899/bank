package com.zhiling.bank.util;



import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

/**
 * @author LiZheng
 * @date 2020/3/16 20:19
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    private RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer(Charset.forName("gbk")));
        MyRedisSerializer<Object> mrs = new MyRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(mrs);
        redisTemplate.setConnectionFactory(factory);
        ParserConfig.getGlobalInstance().addAccept("com.zhiling.bank.entity");
        return redisTemplate;
    }



}

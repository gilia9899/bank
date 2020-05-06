package com.zhiling.bank.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @author LiZheng
 * @date 2020/3/16 20:47
 */
public class MyRedisSerializer<T> implements RedisSerializer<T> {

    private Class<T> cls;

    public MyRedisSerializer(Class<T> cls){
        this.cls = cls;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(t == null){
            return new byte[0];
        }

        String jsonStr = JSON.toJSONString(t, SerializerFeature.WriteClassName);
        System.out.println("jsonStr:" + jsonStr);
        return jsonStr.getBytes(Charset.forName("utf-8"));

    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null || bytes.length <= 0){
            return null;
        }
        String str = new String(bytes,Charset.forName("utf-8"));
        return JSON.parseObject(str,cls);
    }
}

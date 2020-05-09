package com.zhiling.bank.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MySerializer<T> implements RedisSerializer<T> {
	
	private Class<T> clazz;
	
	public MySerializer(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
			if (t == null) {
				return null;
			}
			return JSONObject.toJSONString(t, SerializerFeature.WriteClassName).getBytes(Charset.forName("utf-8"));
		
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		return JSONObject.parseObject(new String(bytes,Charset.forName("utf-8")), clazz);
	}

}

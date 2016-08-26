/**
 * 
 */
package com.xqsight.data.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author xqsight-jerry
 * 
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
	
	private Class<T> clazz;
	
	public FastJsonRedisSerializer(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public byte[] serialize(T t) throws SerializationException {
		return JSON.toJSONBytes(t, SerializerFeature.DisableCircularReferenceDetect);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		return JSON.parseObject(bytes, clazz, Feature.DisableCircularReferenceDetect);
	}
}

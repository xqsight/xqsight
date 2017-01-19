/**
 * 
 */
package com.xqsight.data.redis.sample;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import redis.clients.jedis.Connection;
import redis.clients.jedis.Protocol.Command;

import com.xqsight.data.redis.core.FastJsonRedisTemplate;
import com.xqsight.data.redis.sample.model.Data;
import com.xqsight.data.redis.sample.model.SubData;
import com.xqsight.data.redis.serializer.FastJsonRedisSerializer;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/xqsight-data-redis.xml",
		"classpath:config/data/redis/test-xqsight-data-redis.xml",})
public class RedisSample {
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Data> redisTemplate;  
	
	@Autowired
	private FastJsonRedisTemplate fastJsonRedisTemplate;
	@Test
	public void save() {
		final Data data = new Data();
		data.setDataId("333");
		List<SubData> list = new ArrayList<SubData>();
		SubData sd = new SubData();
		sd.setDataId("444");
		list.add(sd);
		data.setList(list);
		
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<Data>(Data.class));
		
		redisTemplate.execute(new RedisCallback<Data>() {
			@SuppressWarnings("unchecked")
			@Override
			public Data doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(
						redisTemplate.getStringSerializer().serialize("data.uid." + data.getDataId()),
						((FastJsonRedisSerializer<Data>) redisTemplate.getValueSerializer()).serialize(data));
				return null;
			}
		});
		
		
		data.setDataId("222");
		fastJsonRedisTemplate.setValue("4343", data);
	}
	
	/**
	 * 测试Spring-data-redis和jedis的版本是否搭配
	 */
	@Test
	public void testVersion() {
	    System.out.println("Init");
	    Method sendCommandMethod = ReflectionUtils.findMethod(Connection.class, "sendCommand", new Class[] { Command.class,
	                                                                                             byte[][].class });
	    ReflectionUtils.makeAccessible(sendCommandMethod);
	}
	
	@Test
	public void read() throws Exception {
		final String dataId = "333";
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<Data>(Data.class));
		Data executedData = redisTemplate.execute(new RedisCallback<Data>() {
			@SuppressWarnings("unchecked")
			@Override
			public Data doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						"data.uid." + dataId);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					Data data = ((FastJsonRedisSerializer<Data>) redisTemplate.getValueSerializer()).deserialize(value);
					return data;
				}
				return null;
			}
		});
		
		System.out.println(executedData.getDataId());
		System.out.println(executedData.getList().get(0).getDataId());
		
		executedData = fastJsonRedisTemplate.getValue("4343", Data.class);
		
		System.out.println(executedData.getDataId());
		System.out.println(executedData.getList().get(0).getDataId());
	}
	
	@Test
	public void delete() {
		final String dataId = "222";
		redisTemplate.execute(new RedisCallback<Data>() {
			@Override
			public Data doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(
						"data.uid." + dataId);
				byte[] key2 = redisTemplate.getStringSerializer().serialize(
						"4343");
				long lon = connection.del(key, key2);
				System.out.println("long=" + lon);
				if (connection.exists(key)) {
//					long lon = connection.del(key);
					System.out.println("long=" + lon);
				}
				return null;
			}
		});
		
	}
	
}

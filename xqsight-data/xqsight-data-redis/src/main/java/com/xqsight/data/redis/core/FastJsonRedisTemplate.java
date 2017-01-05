package com.xqsight.data.redis.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 该类处理的所有内容必须符合： 1、Key 都是String 2、Value 都是JsonString
 * @author xqsight-jerry
 */
@Component
public class FastJsonRedisTemplate {
	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 通过参数<tt>pattern</tt>得到Redis中键值集合<tt>Set<String></tt>
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) {
		return stringRedisTemplate.keys(pattern);
	}
	/**
	 * Setter method for property <tt>stringRedisTemplate</tt>.
	 * @param stringRedisTemplate value to be assigned to property stringRedisTemplate
	 */
	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}
	/**
	 * 放入redis，不设置超时时间
	 * @param key
	 * @param value
	 */
	public void setValue(String key, Object value) {
		setValue(key, value, 0);
	}
	/**
	 * 放入redis，设置超时时间
	 * @param key
	 * @param value
	 * @param expiredMilliSec 微秒
	 */
	public void setValue(String key, Object value, long expiredMilliSec) {
		setValue(key, value, expiredMilliSec, TimeUnit.MILLISECONDS);
	}
	/**
	 * 放入redis，设置超时时间
	 * @param key
	 * @param value
	 * @param timeout
	 * @param timeUnit
	 */
	public void setValue(String key, Object value, long timeout, TimeUnit timeUnit) {
		if (value != null) {
			ValueOperations<String, String> valueOper = stringRedisTemplate.opsForValue();
			String strValue = JSON.toJSONString(value);
			if (timeout <= 0) {
				valueOper.set(key, strValue);
			} else {
				valueOper.set(key, strValue, timeout, timeUnit);
			}
			logger.info("set redis key=" + key + ",value=" + strValue);
		} else {
			logger.warn("fail to set redis key=" + key + ",but value is null");
		}
	}
	/**
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void setHash(String key, String hashKey, Object value) {
		setHash(key, hashKey, value, 0, null);
	}
	/**
	 * @param key
	 * @param hashKey
	 * @param value
	 * @param timeout
	 * @param timeUnit
	 */
	public void setHash(String key, String hashKey, Object value, long timeout, TimeUnit timeUnit) {
		if (value != null) {
			HashOperations<String, String, String> hashOper = stringRedisTemplate.opsForHash();
			String strValue = JSON.toJSONString(value);
			hashOper.put(key, hashKey, strValue);
			if (timeout > 0) {
				stringRedisTemplate.expire(key, timeout, timeUnit);
			}
		} else {
			logger.warn("fail to set redis key=" + key + ",but value is null");
		}
	}
	/**
	 * 通过key取得值
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getHash(String key, String hashKey, Class<T> clazz) throws Exception {
		HashOperations<String, String, String> hashOper = stringRedisTemplate.opsForHash();
		String value = hashOper.get(key, hashKey);
		logger.info("get redis key=" + key + ",hashKey=" + hashKey + ",value=" + value);
		return value == null ? null : JSON.parseObject(value, clazz);
	}
	/**
	 * 通过key取得List值
	 * @param key
	 * @param hashKey
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getHashArray(String key, String hashKey, Class<T> clazz) throws Exception {
		HashOperations<String, String, String> hashOper = stringRedisTemplate.opsForHash();
		String value = hashOper.get(key, hashKey);
		logger.info("get redis key=" + key + ",hashKey=" + hashKey + ",value=" + value);
		return value == null ? null : JSON.parseArray(value, clazz);
	}
	/**
	 * 通过key取得整个hashmap
	 * @param key
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> Map<String, T> getEntireHash(String key, Class<T> clazz) throws Exception {
		HashOperations<String, String, String> hashOper = stringRedisTemplate.opsForHash();
		Map<String, String> value = hashOper.entries(key);
		Map<String, T> newValue = new HashMap<String, T>();
		for (String hashKey : value.keySet()) {
			T val = JSON.parseObject(value.get(hashKey), clazz);
			newValue.put(hashKey, val);
		}
		return newValue;
	}
	/**
	 * @param key
	 * @param value
	 * @param score
	 */
	public void setZSet(String key, Object value, double score) {
		setZSet(key, value, score, 0, null);
	}
	/**
	 * @param key
	 * @param value
	 * @param score
	 */
	public void setZSet(String key, Object value, double score, long timeout, TimeUnit timeUnit) {
		if (value != null) {
			ZSetOperations<String, String> zSetOper = stringRedisTemplate.opsForZSet();
			String strValue = JSON.toJSONString(value);
			zSetOper.add(key, strValue, score);
			logger.info("set redis key=" + key + ",value=" + strValue);
			if (timeout > 0) {
				stringRedisTemplate.expire(key, timeout, timeUnit);
			}
		} else {
			logger.warn("fail to set redis key=" + key + ",but value is null");
		}
	}
	/**
	 * @param <T>
	 * @param key
	 * @param value
	 * @param score
	 */
	public <T> List<T> getZSet(String key, double min, double max, Class<T> clazz) {
		ZSetOperations<String, String> zSetOper = stringRedisTemplate.opsForZSet();
		Set<String> rangeByScore = zSetOper.rangeByScore(key, min, max);
		if (rangeByScore != null) {
			List<T> tList = new ArrayList<T>();
			for (String value : rangeByScore) {
				tList.add(JSON.parseObject(value, clazz));
			}
			logger.info("get redis key=" + key + ",value is zset, size=" + tList.size());
			return tList;
		}
		return Collections.emptyList();
	}
	/**
	 * 通过key取得值
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getValue(String key, Class<T> clazz) throws Exception {
		ValueOperations<String, String> valueOper = stringRedisTemplate.opsForValue();
		String value = valueOper.get(key);
		logger.info("get redis key=" + key + ",value=" + value);
		return value == null ? null : JSON.parseObject(value, clazz);
	}
	/**
	 * 通过key得到array
	 * @param key
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getValueArray(String key, Class<T> clazz) throws Exception {
		ValueOperations<String, String> valueOper = stringRedisTemplate.opsForValue();
		String value = valueOper.get(key);
		logger.info("get redis key=" + key + ",value=" + value);
		return value == null ? null : JSON.parseArray(value, clazz);
	}
	/**
	 * 通过key删除值
	 * @param key
	 */
	public void del(String key) {
		if (stringRedisTemplate.hasKey(key)) {
			stringRedisTemplate.delete(key);
		}
		logger.info("delete redis key=" + key);
	}
	/**
	 * 删除一个集合内的key值
	 * @param keys
	 */
	public void del(Collection<String> keys) {
		stringRedisTemplate.delete(keys);
	}
	/**
	 * 删除一个<tt>pattern</tt>描述的key值
	 * @param keys
	 */
	public void delWithPattern(String pattern) {
		Set<String> keys = keys(pattern);
		stringRedisTemplate.delete(keys);
	}
	/**
	 * 通过key删除值
	 * @param key
	 */
	public void delHash(String key, String hashKey) {
		HashOperations<String, String, String> hashOper = stringRedisTemplate.opsForHash();
		if (hashOper.hasKey(key, hashKey)) {
			hashOper.delete(key, hashKey);
		}
		logger.info("delete redis key=" + key + ",hashKey=" + hashKey);
	}
}

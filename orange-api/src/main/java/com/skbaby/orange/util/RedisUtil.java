package com.skbaby.orange.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil{

	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	public void save(String key, String object) {
		// 半年
		redisTemplate.opsForValue().set(key, object, 60, TimeUnit.DAYS);
	}

	public String get(String key) {
		String value = redisTemplate.opsForValue().get(key);
		if (value == null) {
			return null;
		}
		return value;
	}

	public void remove(String key) {
		redisTemplate.delete(key);
	}
}

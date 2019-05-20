package com.skbaby.orange.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil{

	@Autowired
	private static RedisTemplate<String, String> redisTemplate;


	public static void save(String key, String object) {
		// 不设置过期时间
		redisTemplate.opsForValue().set(key, object);
	}

	public static String get(String key) {
		String value = redisTemplate.opsForValue().get(key);
		if (value == null) {
			return null;
		}
		return value;
	}

	public static void remove(String key) {
		redisTemplate.delete(key);
	}
}

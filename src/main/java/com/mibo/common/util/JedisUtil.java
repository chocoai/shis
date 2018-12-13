package com.mibo.common.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import com.jfinal.kit.LogKit;
import com.mibo.common.constant.Global;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

public class JedisUtil {

	private static boolean initialized = false;
	private static JedisPool jedisPool = null;

	private static void initialize() {
		if (initialized)
			return;
		jedisPool = getJedisPool();
		initialized = true;
	}

	private static JedisPool getJedisPool() {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(200);
		config.setMaxIdle(50);
		config.setMinIdle(8);// 设置最小空闲数
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		// Idle时进行连接扫描
		config.setTestWhileIdle(true);
		// 表示idle object evitor两次扫描之间要sleep的毫秒数
		config.setTimeBetweenEvictionRunsMillis(30000);
		// 表示idle object evitor每次扫描的最多的对象数
		config.setNumTestsPerEvictionRun(10);
		// 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object
		// evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
		config.setMinEvictableIdleTimeMillis(60000);
		String host = Global.getKeys("redis.host");
		int port = Integer.valueOf(Global.getKeys("redis.port"));
		String password = Global.getKeys("redis.password");
		int timeout = Integer.valueOf(Global.getKeys("redis.timeout"));
		int database = Integer.valueOf(Global.getKeys("redis.database"));
		jedisPool = new JedisPool(config, host, port, timeout, password, database);
		return jedisPool;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key 键
	 * @return 值
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = getResource();
		try {
			if (jedis.exists(key)) {
				value = jedis.get(key);
				value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
			}
		} catch (Exception e) {
			LogKit.error("redis get key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 设置缓存
	 * 
	 * @param key          键
	 * @param value        值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String set(String key, String value, int cacheSeconds) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.set(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LogKit.error("redis set key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取List缓存
	 * 
	 * @param key 键
	 * @return 值
	 */
	public static List<String> getList(String key) {
		List<String> value = null;
		Jedis jedis = getResource();
		;
		try {
			if (jedis.exists(key)) {
				value = jedis.lrange(key, 0, -1);
			}
		} catch (Exception e) {
			LogKit.error("redis getList key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 设置List缓存
	 * 
	 * @param key          键
	 * @param value        值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static long setList(String key, List<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = getResource();
		try {
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.rpush(key, (String[]) value.toArray());
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LogKit.error("redis setList key======>" + key + e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向List缓存中添加值
	 * 
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public static long listAdd(String key, String... value) {
		long result = 0;
		Jedis jedis = getResource();
		try {
			result = jedis.rpush(key, value);
		} catch (Exception e) {
			LogKit.error("redis listAdd key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key 键
	 * @return 值
	 */
	public static Set<String> getSet(String key) {
		Set<String> value = null;
		Jedis jedis = getResource();
		try {
			if (jedis.exists(key)) {
				value = jedis.smembers(key);
			}
		} catch (Exception e) {
			LogKit.error("redis getSet key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 设置Set缓存
	 * 
	 * @param key          键
	 * @param value        值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static long setSet(String key, Set<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = getResource();
		try {
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.sadd(key, (String[]) value.toArray());
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LogKit.error("redis setSet key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向Set缓存中添加值
	 * 
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public static long setSetAdd(String key, String... value) {
		long result = 0;
		Jedis jedis = getResource();
		try {
			result = jedis.sadd(key, value);
		} catch (Exception e) {
			LogKit.error("redis setSetAdd key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取Map缓存
	 * 
	 * @param key 键
	 * @return 值
	 */
	public static Map<String, String> getMap(String key) {
		Map<String, String> value = null;
		Jedis jedis = getResource();
		try {
			if (jedis.exists(key)) {
				value = jedis.hgetAll(key);
			}
		} catch (Exception e) {
			LogKit.error("redis getMap key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 设置Map缓存
	 * 
	 * @param key          键
	 * @param value        值
	 * @param cacheSeconds 超时时间，0为不超时
	 * @return
	 */
	public static String setMap(String key, Map<String, String> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = getResource();
		try {
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.hmset(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			LogKit.error("redis setMap key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向Map缓存中添加值
	 * 
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public static String mapPut(String key, Map<String, String> value) {
		String result = null;
		Jedis jedis = getResource();
		try {
			result = jedis.hmset(key, value);
		} catch (Exception e) {
			LogKit.error("redis mapPut key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 移除Map缓存中的值
	 * 
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public static long mapRemove(String key, String mapKey) {
		long result = 0;
		Jedis jedis = getResource();
		try {
			result = jedis.hdel(key, mapKey);
		} catch (Exception e) {
			LogKit.error("redis mapRemove key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 判断Map缓存中的Key是否存在
	 * 
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public static boolean mapExists(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = getResource();
		try {
			result = jedis.hexists(key, mapKey);
		} catch (Exception e) {
			LogKit.error("redis mapExists key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 删除缓存
	 * 
	 * @param key 键
	 * @return
	 */
	public static long del(String key) {
		long result = 0;
		Jedis jedis = getResource();
		try {
			if (jedis.exists(key)) {
				result = jedis.del(key);
			}
		} catch (Exception e) {
			LogKit.error("redis del key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 缓存是否存在
	 * 
	 * @param key 键
	 * @return
	 */
	public static boolean exists(String key) {
		boolean result = false;
		Jedis jedis = getResource();
		try {
			result = jedis.exists(key);
		} catch (Exception e) {
			LogKit.error("redis exists key======>" + key);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取资源
	 * 
	 * @return
	 * @throws JedisException
	 */
	public static Jedis getResource() throws JedisException {
		Jedis jedis = null;
		try {
			initialize();
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			LogKit.error("redis getResource ");
		}
		return jedis;
	}

	/**
	 * 释放资源
	 * 
	 * @param jedis
	 * @param isBroken
	 */
	public static void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
}

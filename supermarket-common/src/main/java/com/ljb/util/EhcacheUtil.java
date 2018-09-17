package com.ljb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheUtil {
	private static Logger logger = LoggerFactory.getLogger(EhcacheUtil.class);
	private static CacheManager cacheManager = CacheManager.create();

	/**
	 * 添加缓存
	 */
	public static void put(String name, String k, Object v) {
		Cache cache = cacheManager.getCache(name);
		Element element = new Element(k, v);
		cache.put(element);
		putLog("添加缓存", name, k, v);
	}

	/**
	 * 获取缓存
	 */
	public static Object get(String name, String k) {
		Cache cache = cacheManager.getCache(name);
		Element element = cache.get(k);
		Object v = null;
		v = element == null ? null : element.getObjectValue();
		putLog("获取缓存", name, k, v);
		return v;
	}

	/**
	 * 清除所有缓存
	 */
	public static void removeAll(String name) {
		Cache cache = cacheManager.getCache(name);
		cache.removeAll();
		putLog("清除所有缓存", name, null, null);
	}

	/**
	 * 清除指定缓存
	 */
	public static void remove(String name, String k) {
		Cache cache = cacheManager.getCache(name);
		boolean b = cache.remove(k);
		putLog("清除指定缓存", name, k, b);
	}

	/**
	 * 日志打印
	 */
	private static void putLog(String method, String name, String k, Object v) {
		logger.info(method + ": name=" + name + ", K=" + k + ", V=" + v);
	}
}

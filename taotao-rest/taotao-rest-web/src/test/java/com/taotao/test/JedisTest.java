package com.taotao.test;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * Jedis测试
 * @author Administrator
 *
 */
public class JedisTest {
	/**
	 * 测试单个版redis
	 */
	@Test
	public void testSingle() {
		JedisPool pool = new JedisPool("192.168.21.131", 6379);
		Jedis jedis = pool.getResource();
		jedis.set("key", "test Jedis");
		String key = jedis.get("key");
		System.out.println(key);
		
		jedis.close();
		pool.close();
	}
	
	/**
	 * 测试集群版jedis
	 */
	@Test
	public void testCluster() {
		HashSet nodes = new HashSet();
		nodes.add(new HostAndPort("192.168.21.131", 7001));
		nodes.add(new HostAndPort("192.168.21.131", 7002));
		nodes.add(new HostAndPort("192.168.21.131", 7003));
		nodes.add(new HostAndPort("192.168.21.131", 7004));
		nodes.add(new HostAndPort("192.168.21.131", 7005));
		nodes.add(new HostAndPort("192.168.21.131", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		
		cluster.set("key", "test cluster");
		String key = cluster.get("key");
		System.out.println(key);
	}
	
	
	/**
	 * 测试单个版redis
	 */
	@Test
	public void testSpringSingle() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) context.getBean("jedisPool");
		Jedis jedis = pool.getResource();
		String key = jedis.get("key");
		System.out.println(key);
		
		jedis.close();
		pool.close();
	}
	
	/**
	 * 测试集群版jedis
	 */
	@Test
	public void testSpringCluster() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		
		JedisCluster cluster = (JedisCluster) context.getBean("jedisCluster");
		String key = cluster.get("key");
		System.out.println(key);
	}
}

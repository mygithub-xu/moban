package com.dhlg.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;


import redis.clients.jedis.JedisPoolConfig;
/**
 * 描述
 * User:whfch
 * Date:2020/4/19
 * Time:1:18
 */

@Configuration
@EnableAutoConfiguration
public class RedisCacheConfig extends CachingConfigurerSupport{



}
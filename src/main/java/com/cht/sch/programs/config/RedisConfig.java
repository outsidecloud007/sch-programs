package com.cht.sch.programs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    
    @Value("${spring.redis.host}")
    String redisHostname;
    
    @Value("${spring.redis.port}")
    int redisPort;

	@Bean
	JedisConnectionFactory connectionFactory() {
	    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
	    connectionFactory.setHostName(redisHostname);
	    connectionFactory.setPort(redisPort);
		return connectionFactory;
	}
	 
	@Bean
	public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory connectionFactory) {

		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(connectionFactory);
		template.setHashValueSerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
		return template;
	}
}

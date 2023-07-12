package com.example.dkmh.config.cache;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;

@Configuration
@EnableCaching
public class RedisCacheConfig {
	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

		// Cấu hình thời gian tồn tại của cache
		configuration = configuration.entryTtl(Duration.ofMinutes(10)); // Thời gian tồn tại 10 phút
		// Cấu hình sử dụng PrefixedCacheKeys
		configuration = configuration.computePrefixWith(cacheName -> "prefix_" + cacheName + "_");

		// Cấu hình sử dụng Jackson2JsonRedisSerializer để serialize/deserialize giá trị
		// cache thành JSON
//		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//		RedisSerializationContext.SerializationPair<Object> serializationPair = RedisSerializationContext.SerializationPair
//				.fromSerializer(serializer);
//		configuration = configuration.serializeValuesWith(serializationPair);

		// Cấu hình sử dụng GenericJackson2JsonRedisSerializer để serialize/deserialize
		// giá trị cache thành JSON
		GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
		RedisSerializationContext.SerializationPair<Object> serializationPair = RedisSerializationContext.SerializationPair
				.fromSerializer(serializer);
		configuration = configuration.serializeValuesWith(serializationPair);
		// Cấu hình sử dụng StringRedisSerializer để serialize/deserialize key của cache
		// thành String
//		StringRedisSerializer keySerializer = new StringRedisSerializer();
//		configuration = configuration.serializeKeysWith(keySerializer);

		// Cấu hình sử dụng Jackson2JsonRedisSerializer để serialize/deserialize key của
		// cache thành JSON
//		Jackson2JsonRedisSerializer<Object> keySerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//		RedisSerializationContext.SerializationPair<Object> keySerializationPair = RedisSerializationContext.SerializationPair
//				.fromSerializer(keySerializer);
//		configuration = configuration.serializeKeysWith(keySerializationPair);

		// Cấu hình sử dụng StringRedisSerializer để serialize/deserialize key và giá
		// trị của cache thành String
//		configuration = configuration.serializeKeysWith(
//				RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
//		configuration = configuration.serializeValuesWith(
//				RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));

		return configuration;
	}

	// Cấu hình Redis Cache Manager
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration configuration = cacheConfiguration();

		return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(configuration).build();
	}
}
package fun.steven.bookstore.utils.cache;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import java.time.Duration;

@Configuration
public class RedisConfig implements CachingConfigurer {

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                // 设置默认缓存过期时间为 1 小时
                .entryTtl(Duration.ofHours(1))
                // 禁止缓存 null 值，防止缓存穿透
                .disableCachingNullValues()
                // 设置值的序列化器为 JSON 格式，可读性更高
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new RedisErrorHandler();
    }
}
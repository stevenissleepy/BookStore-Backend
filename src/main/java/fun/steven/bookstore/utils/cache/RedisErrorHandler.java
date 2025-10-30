package fun.steven.bookstore.utils.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class RedisErrorHandler implements CacheErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RedisErrorHandler.class);

    @Override
    public void handleCacheGetError(
            @NonNull RuntimeException exception,
            @NonNull Cache cache,
            @NonNull Object key) {
        logger.error("Unable to get from cache " + cache.getName() + " : " + exception.getMessage());
    }

    @Override
    public void handleCachePutError(
            @NonNull RuntimeException exception,
            @NonNull Cache cache,
            @NonNull Object key,
            @Nullable Object value) {
        logger.error("Unable to put into cache " + cache.getName() + " : " + exception.getMessage());
    }

    @Override
    public void handleCacheEvictError(
            @NonNull RuntimeException exception,
            @NonNull Cache cache,
            @NonNull Object key) {
        logger.error("Unable to evict from cache " + cache.getName() + " : " + exception.getMessage());
    }

    @Override
    public void handleCacheClearError(
            @NonNull RuntimeException exception,
            @NonNull Cache cache) {
        logger.error("Unable to clear cache " + cache.getName() + " : " + exception.getMessage());
    }
}
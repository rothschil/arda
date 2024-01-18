package io.github.rothschil.common.config.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.rothschil.common.config.CaffeineCacheInitializer;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 本地站点 磁盘缓存
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
@Configuration
public class CacheConfig {


    /** 本地缓存处理构造函数
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @return Cache
     **/
    @Bean("localCacheManager")
    public Cache<String, Object> caffeineCache() {
        return Caffeine.newBuilder().expireAfterWrite(600, TimeUnit.SECONDS).initialCapacity(100).maximumSize(1000).build();
    }

    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = CaffeineCacheInitializer.initCaffeineCache();
        if (CollectionUtils.isEmpty(caches)) {
            return cacheManager;
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }


}

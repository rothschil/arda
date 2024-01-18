package io.github.rothschil.common.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CaffeineCacheInitializer {

    public static List<CaffeineCache> initCaffeineCache() {
        List<CaffeineCache> caffeineCacheList = new ArrayList<>();
        CaffeineCache userCache = new CaffeineCache(CacheKey.USER_CACHE_KEY, Caffeine.newBuilder().recordStats()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .maximumSize(100)
                .build());
        caffeineCacheList.add(userCache);

        //将所有需要定义的CaffeineCache添加到容器中
        //....
        return caffeineCacheList;
    }
}

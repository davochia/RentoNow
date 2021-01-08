package com.example.demo.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("guests"),
                new ConcurrentMapCache("guest"),
                new ConcurrentMapCache("hosts"),
                new ConcurrentMapCache("host"),
                new ConcurrentMapCache("host_properties"),
                new ConcurrentMapCache("admins"),
                new ConcurrentMapCache("admin"),
                new ConcurrentMapCache("properties"),
                new ConcurrentMapCache("property"),
                new ConcurrentMapCache("filtered_properties"),
                new ConcurrentMapCache("reservations"),
                new ConcurrentMapCache("reservation"),
                new ConcurrentMapCache("reservations_by_guest"),
                new ConcurrentMapCache("reservations_by_host"),
                new ConcurrentMapCache("reservations_by_property")
            )
        );
        return cacheManager;
    }
}

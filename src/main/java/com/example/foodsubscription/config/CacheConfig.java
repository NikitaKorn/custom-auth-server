package com.example.foodsubscription.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@Slf4j
@ConditionalOnProperty(value = "service.caching.enable", havingValue = "true")
public class CacheConfig {
    public CacheConfig() {
        log.info("Caching enable");
    }
}

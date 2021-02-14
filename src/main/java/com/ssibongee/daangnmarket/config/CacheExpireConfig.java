package com.ssibongee.daangnmarket.config;

import java.time.Duration;

public class CacheExpireConfig {

    public static final Duration DEFAULT_CACHE_EXPIRE_TIME = Duration.ofMinutes(10L);

    public static final Duration POST_CACHE_EXPIRE_TIME = Duration.ofMinutes(5L);

    public static final Duration CATEGORY_CACHE_EXPIRE_TIME = Duration.ofDays(1L);
}

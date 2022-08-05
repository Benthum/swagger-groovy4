package com.benthum.swagger

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Accessor {
    @Value('${config.redisIdleTimeout}')
    Long redisCacheIdleTimeout

    @Value('${spring.cache.redis.time-to-live}')
    Long redisCacheTtl
}

package br.com.fleury.commons.base

import br.com.fleury.commons.utils.RateLimiter
import java.util.concurrent.TimeUnit

abstract class BaseRepository(
    timeout: Int = 1,
    timeUnit: TimeUnit = TimeUnit.HOURS
) {
    protected var rateLimiter: RateLimiter<String> = RateLimiter(timeout, timeUnit)
}
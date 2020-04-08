package br.com.fleury.commons_android.base

import br.com.fleury.commons_android.utils.RateLimiter
import java.util.concurrent.TimeUnit

abstract class BaseRepository(
    timeout: Int = 1,
    timeUnit: TimeUnit = TimeUnit.HOURS
) {
    protected var rateLimiter: RateLimiter<String> = RateLimiter(timeout, timeUnit)
}
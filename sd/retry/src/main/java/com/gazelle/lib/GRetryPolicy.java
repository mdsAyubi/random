package com.gazelle.lib;

public interface GRetryPolicy {
    int DEFAULT_RETRY_ATTEMPTS = 1;

    default boolean canRetry(GRetryContext retryContext) {
        return true;
    }
}

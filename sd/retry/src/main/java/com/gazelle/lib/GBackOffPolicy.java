package com.gazelle.lib;

public interface GBackOffPolicy {
    long DEFAULT_TIME_INTERVAL = 1000;

    default long getNextTimeInterval(GRetryContext retryContext) {
        return DEFAULT_TIME_INTERVAL;
    }
}

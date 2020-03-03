package com.gazelle.lib;

public class ExponentialBackOffPolicy implements GBackOffPolicy {
    private long initialTimeInterval;
    private long maxTimeInterval;
    private int exponent;

    private ThreadLocal<Long> currentTimeInterval;

    public ExponentialBackOffPolicy(){
        this.initialTimeInterval = DEFAULT_TIME_INTERVAL;
        this.maxTimeInterval = Long.MAX_VALUE;
        this.exponent = 2;
        this.currentTimeInterval = ThreadLocal.withInitial(() -> initialTimeInterval);
    }

    public ExponentialBackOffPolicy(final long initialTimeInterval, final long maxTimeInterval, final int exponent){
        this.initialTimeInterval = initialTimeInterval;
        this.maxTimeInterval = maxTimeInterval;
        this.exponent = exponent;
        this.currentTimeInterval = ThreadLocal.withInitial(() -> initialTimeInterval);
    }

    @Override
    public long getNextTimeInterval(GRetryContext retryContext) {
        if(retryContext.getRetryCount() == 0){
            currentTimeInterval.set(0L);
        }
        final long nextTimeInterval = initialTimeInterval * (long)Math.pow(exponent, retryContext.getRetryCount());
        if(nextTimeInterval > maxTimeInterval) {
            throw new RuntimeException("Backoff limit exceeded");
        }
        currentTimeInterval.set(nextTimeInterval);
        return nextTimeInterval;
    }
}

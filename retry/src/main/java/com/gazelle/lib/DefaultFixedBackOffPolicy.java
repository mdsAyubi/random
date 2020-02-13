package com.gazelle.lib;

public class DefaultFixedBackOffPolicy implements GBackOffPolicy {

    private long nextTimeInterval;

    public DefaultFixedBackOffPolicy(){
        nextTimeInterval = DEFAULT_TIME_INTERVAL;
    }

    public DefaultFixedBackOffPolicy(long nextTimeInterval){
        this.nextTimeInterval = nextTimeInterval;
    }

    @Override
    public long getNextTimeInterval(GRetryContext retryContext) {
        return nextTimeInterval;
    }
}

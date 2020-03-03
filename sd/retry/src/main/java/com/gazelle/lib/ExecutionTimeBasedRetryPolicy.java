package com.gazelle.lib;

public class ExecutionTimeBasedRetryPolicy implements GRetryPolicy {

    private static final long DEFAULT_MAX_EXECUTION_TIME = 1000;

    private long maxExecutionTimeMillis;

    public ExecutionTimeBasedRetryPolicy(long maxExecutionTimeMillis) {
        this.maxExecutionTimeMillis = maxExecutionTimeMillis;
    }

    public ExecutionTimeBasedRetryPolicy() {
        this.maxExecutionTimeMillis = DEFAULT_MAX_EXECUTION_TIME;
    }

    @Override
    public boolean canRetry(GRetryContext retryContext) {
        if(retryContext.getRetryCount() == 0) {
            return true;
        }
        else {
            return retryContext.getExecutionTimes().get(retryContext.getRetryCount() - 1) <= maxExecutionTimeMillis;
        }
    }

    public long getMaxExecutionTimeMillis() {
        return maxExecutionTimeMillis;
    }
}

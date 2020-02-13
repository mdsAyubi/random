package com.gazelle.lib;

public class DefaultRetryPolicy implements GRetryPolicy {

    private int maxRetryAttempts;

    public DefaultRetryPolicy(int maxRetryAttempts) {
        this.maxRetryAttempts = maxRetryAttempts;
    }

    public DefaultRetryPolicy() {
        this.maxRetryAttempts = DEFAULT_RETRY_ATTEMPTS;
    }

    @Override
    public boolean canRetry(GRetryContext retryContext) {
        return retryContext.getRetryCount() <= maxRetryAttempts;
    }
}

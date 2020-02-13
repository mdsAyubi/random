package com.gazelle.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GRetry<T> {

    private GRetryPolicy gRetryPolicy;

    private GExceptionPolicy gExceptionPolicy;

    private GBackOffPolicy gBackOffPolicy;

    public GRetry(GRetryPolicy gRetryPolicy, GBackOffPolicy gBackOffPolicy, GExceptionPolicy gExceptionPolicy) {
        this.gRetryPolicy = gRetryPolicy;
        this.gBackOffPolicy = gBackOffPolicy;
        this.gExceptionPolicy = gExceptionPolicy;
    }

    public T tryOf(final Supplier<T> r) {
        final GRetryContext retryContext = GRetryContext.createEmptyContext();
        do {
            try {
                if (retryContext.getRetryCount() > 0) {
                    Thread.sleep(gBackOffPolicy.getNextTimeInterval(retryContext));
                }
                return doTryOf(r, retryContext);
            } catch (Exception e) {
                handleException(retryContext, e);
            }
        } while(gRetryPolicy.canRetry(retryContext));
        throw new RuntimeException("All retry attempts exhausted", gExceptionPolicy.getConsolidatedExceptions(retryContext.getExceptions()));
    }

    private T doTryOf(Supplier<T> r, GRetryContext retryContext) throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        try {
            return r.get();
        } finally {
            retryContext.saveExecutionTime(System.currentTimeMillis() - startTime);
        }
    }

    private void handleException(GRetryContext retryContext, Exception e) {
        retryContext.incrementRetryCount();
        retryContext.saveException(e);
        if (gExceptionPolicy.isExceptionRetriable(e.getClass())) {
            return;
        }
        else if(gExceptionPolicy.isExceptionIgnorable(e.getClass())){
            throw new RuntimeException("Died because of", e);
        }
        else {
            throw new RuntimeException("Unhandled by exception policy", e);
        }
    }

}

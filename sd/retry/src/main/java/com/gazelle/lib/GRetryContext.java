package com.gazelle.lib;

import java.util.*;

/**
 * Domain to hold the context of retries, this is not thread safe.
 * <p>
 * Every retry operation has its own context which is mutated.
 */
public class GRetryContext {
    /**
     * The retry count
     */
    private int retryCount;

    /**
     * List of exception thrown by different retry attempts
     */
    private List<Throwable> exceptions;

    /**
     * Execution time
     */

    private List<Long> executionTimes;

    /**
     * Any meta data for different attempts
     */
    private List<Map<String, Object>> operationInfo;

    private GRetryContext(){
        this.retryCount = 0;
        this.exceptions = new ArrayList<>();
        this.executionTimes = new ArrayList<>();
        this.operationInfo = new ArrayList<>();
    }

    public static GRetryContext createEmptyContext() {
        return new GRetryContext();
    }

    @Override
    public String toString() {
        return "GRetryContext{" +
                "retryCount=" + retryCount +
                ", exceptions=" + exceptions +
                ", executionTimes=" + executionTimes +
                ", operationInfo=" + operationInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GRetryContext that = (GRetryContext) o;
        return retryCount == that.retryCount &&
                Objects.equals(exceptions, that.exceptions) &&
                Objects.equals(executionTimes, that.executionTimes) &&
                Objects.equals(operationInfo, that.operationInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retryCount, exceptions, executionTimes, operationInfo);
    }

    public int getRetryCount() {
        return retryCount;
    }

    public List<Throwable> getExceptions() {
        return exceptions;
    }

    public List<Map<String, Object>> getOperationInfo() {
        return operationInfo;
    }

    public List<Long> getExecutionTimes() {
        return executionTimes;
    }

    public void incrementRetryCount() {
        this.retryCount++;
    }

    public void saveExecutionTime(long executionTime) {
        this.executionTimes.add(executionTime);
    }

    public void saveException(Exception e) {
        this.exceptions.add(e);
    }
}

package com.gazelle.lib;

import java.sql.SQLException;
import java.util.Set;

public class DefaultExceptionPolicy implements GExceptionPolicy {

    private Set<Class<? extends Throwable>> exceptionsToRetryFor;

    private Set<Class<? extends Throwable>> exceptionsToDieFor;

    public DefaultExceptionPolicy(){
        this.exceptionsToRetryFor = Set.of(RuntimeException.class, SQLException.class);
        this.exceptionsToDieFor = Set.of();
    }

    @Override
    public Set<Class<? extends Throwable>> getExceptionsToRetryFor() {
        return this.exceptionsToRetryFor;
    }

    @Override
    public boolean isExceptionRetriable(Class<? extends Exception> aClass) {
        return this.exceptionsToRetryFor.contains(aClass);
    }

    @Override
    public boolean isExceptionIgnorable(Class<? extends Exception> aClass) {
        return this.exceptionsToDieFor.contains(aClass);
    }
}

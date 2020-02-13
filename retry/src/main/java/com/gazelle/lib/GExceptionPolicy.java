package com.gazelle.lib;

import java.util.List;
import java.util.Set;

public interface GExceptionPolicy {
    default Set<Class<? extends Throwable>> getExceptionsToRetryFor() {
        return Set.of(Throwable.class);
    }

    default Set<Class<? extends Throwable>> getExceptionsToDieFor() {
        return Set.of();
    }

    default Throwable getConsolidatedExceptions(List<Throwable> exceptions){
        return new RuntimeException("Operation failure", exceptions.get(exceptions.size() - 1));
    }

    boolean isExceptionRetriable(Class<? extends Exception> aClass);

    boolean isExceptionIgnorable(Class<? extends Exception> aClass);
}

package com.rp.inmem.db;

public interface ValueValidator {

    Validation validate(Column column, Object value);
}

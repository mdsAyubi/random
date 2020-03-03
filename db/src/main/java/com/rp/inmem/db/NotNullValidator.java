package com.rp.inmem.db;

public class NotNullValidator implements ValueValidator {
    @Override
    public Validation validate(Column column, Object value) {
        boolean status = value != null;

        if(!status) return  new Validation(status, "Value is null");

        return new Validation(true, null);
    }
}

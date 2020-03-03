package com.rp.inmem.db;

public class LengthValidator implements ValueValidator {
    @Override
    public Validation validate(Column column, Object value) {
        if(!(value instanceof String)){
            return new Validation(false, "Value is not a String");
        }

        String v = (String)value;
        boolean status = v.length() <= 20;

        if(!status) return new Validation(status, "Length is more than 20");

        return new Validation(true, null);
    }
}

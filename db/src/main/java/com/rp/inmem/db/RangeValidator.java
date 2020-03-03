package com.rp.inmem.db;

public class RangeValidator implements ValueValidator{
    @Override
    public Validation validate(Column column, Object value) {
        if(!(value instanceof Integer)){
            return new Validation(false, "Value is not an int");
        }

        Integer v = (Integer)value;
        boolean status = v >= -1024 && v <= 1024;

        if(!status) return new Validation(false, "Range is not in [-1024, 1024]");

        return new Validation(true, null);
    }
}

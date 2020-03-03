package com.rp.inmem.db;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Column {
    private String name;
    private ColumnType columnType;

    private List<ValueValidator> validators = new ArrayList<>();

    public Column(String name, ColumnType columnType, List<ValueValidator> validators) {
        this.name = name;
        this.columnType = columnType;
        this.validators = validators;
    }

    public List<Validation> isValid(Object value){
        final List<Validation> validations = validators.stream().map(v -> v.validate(this, value)).collect(Collectors.toList());
        return validations;
    }

    public String getName() {
        return name;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public List<ValueValidator> getValidators() {
        return validators;
    }
}

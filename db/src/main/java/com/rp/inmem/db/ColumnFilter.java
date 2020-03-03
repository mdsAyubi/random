package com.rp.inmem.db;

public class ColumnFilter {

    String columnName;
    ColumnType columnType;
    Object value;

    public ColumnFilter(String name, ColumnType columnType, Object value) {
        this.columnName = name;
        this.columnType = columnType;
        this.value = value;
    }
}

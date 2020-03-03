package com.rp.inmem.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableMD {
    private String name;
    private String dbName;
    private String schemaName;

    private List<Column> columns = new ArrayList<>();
    private List<Constraint> constraints = new ArrayList<>();

    public TableMD(String name, String dbName, String schemaName, List<Column> columns, List<Constraint> constraints) {
        this.name = name;
        this.dbName = dbName;
        this.schemaName = schemaName;
        this.columns = columns;
        this.constraints = constraints;
    }

    public Map<String, Column> getColumnsMap(){
        return this.getColumns().stream().collect(Collectors.toMap(Column::getName, x->x));
    }

    public String getName() {
        return name;
    }

    public String getDbName() {
        return dbName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }
}

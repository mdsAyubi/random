package com.rp.inmem.db;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Table {
    private Map<Integer, Record> map = new ConcurrentHashMap();
    private TableMD tableMD;

    public Table(TableMD tableMD) {
        this.tableMD = tableMD;
    }

    public DBResponse insert(Record record){

        Set<String> columnNames = record.getMap().keySet();
        Map<String, Column> columnsMap = tableMD.getColumnsMap();


        for (String columnName: columnNames){
            //Check if column exists
            if(!columnsMap.containsKey(columnName)){
                return DBResponse.from(false, new RuntimeException("Column does not exist"));
            }

            Column column = columnsMap.get(columnName);
            Object columnValue = record.getMap().get(columnName);

            //check for validations
            final List<Validation> validations = column.isValid(columnValue);
            boolean allValidationsSuccessfull = validations.stream().allMatch(v -> v.isStatus());

            if(allValidationsSuccessfull){
                continue;
            }else {
                final List<String> failures = validations.stream()
                        .filter(v -> !v.isStatus())
                        .map(v -> v.getMessage())
                        .collect(Collectors.toList());
                return DBResponse.from(false, new RuntimeException("Operation failed because of :" + failures.toString()));
            }

        }
        map.put(record.hashCode(), record);
        return DBResponse.from(true, null);
    }

    public List<Record> filter(FilterCriteria filterCriteria) {
        return null;
    }

    @Override
    public String toString() {
        return "Table{" +
                "map=" + map +
                '}';
    }
}

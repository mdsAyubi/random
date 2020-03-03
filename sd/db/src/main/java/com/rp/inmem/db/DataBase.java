package com.rp.inmem.db;



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase {

    private Map<String, Table> tables = new ConcurrentHashMap<>();

    public Table create(TableMD tableMD) {
        //add various null checks and validations
        Table t = new Table(tableMD);
        tables.put(tableMD.getName(), t);
        return t;
    }

    public DBResponse delete(TableMD tableMD){
        tables.remove(tableMD.getName());
        return DBResponse.from(true, null);
    }
}

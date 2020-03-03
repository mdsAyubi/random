package com.rp.inmem.db;

import java.util.ArrayList;
import java.util.List;

public class FilterCriteria {

    private List<ColumnFilter> filters = new ArrayList<>();

    public void addFilter(String name, ColumnType columnType, Object value){
        filters.add(new ColumnFilter(name, columnType, value));
    }
}

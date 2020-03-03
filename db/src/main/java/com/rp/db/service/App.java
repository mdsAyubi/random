package com.rp.db.service;

import com.rp.inmem.db.*;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        List<ValueValidator> nameValidator = List.of(new NotNullValidator(), new LengthValidator());
        List<ValueValidator> ageValidator = List.of(new RangeValidator());
        List<ValueValidator> heightValidator = List.of(new RangeValidator());

        Column nameColumn = new Column("name", ColumnType.STRING,nameValidator );
        Column ageColumn = new Column("age", ColumnType.INT, ageValidator);
        Column heightColumn = new Column("height", ColumnType.INT, heightValidator);

        TableMD tableMD = new TableMD("user", "user_db","default", List.of(nameColumn, ageColumn, heightColumn), List.of());

        DataBase db = new DataBase();
        Table table = db.create(tableMD);

        System.out.println("Created table");

        Map<String, Object> user = Map.of("name", "ABC", "age", "abc", "height", 6);
        Record userRecord = new Record(user);

        table.insert(userRecord);
        System.out.println("Inserted");

        System.out.println(table.toString());




    }
}

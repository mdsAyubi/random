package com.rp.inmem.db;

public class DBResponse {

    private boolean status;
    private Exception exception;

    private DBResponse(boolean status, Exception e){
        this.status = status;
        this.exception = e;
    }

    public static DBResponse from(boolean status, Exception e){
        return new DBResponse(status, e);
    }
}

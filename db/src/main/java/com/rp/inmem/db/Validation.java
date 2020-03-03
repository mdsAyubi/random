package com.rp.inmem.db;

public class Validation {

    private String message;
    private boolean status;

    public Validation(boolean status, String message) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}

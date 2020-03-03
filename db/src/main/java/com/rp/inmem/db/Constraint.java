package com.rp.inmem.db;

public class Constraint {
    private String name;
    private ConstraintType constraintType;

    public Constraint(String name, ConstraintType constraintType) {
        this.name = name;
        this.constraintType = constraintType;
    }
}

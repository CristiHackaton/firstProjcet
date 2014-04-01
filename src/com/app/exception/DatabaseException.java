package com.app.exception;

public class DatabaseException extends Exception {
    private final String msj;

    public DatabaseException(final String msj) {
        this.msj = msj;
        System.out.println(msj);
    }
}

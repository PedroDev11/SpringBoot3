package com.pepe.jbos.handler;

public class DatabaseException extends RuntimeException {
    private static final long serialVersionUID = -1801235664403547093L;

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}

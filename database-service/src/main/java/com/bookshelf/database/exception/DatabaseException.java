package com.bookshelf.database.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String errorMessage) {
        super(errorMessage);
    }

    public DatabaseException() {
        super("Database exception");
    }
}

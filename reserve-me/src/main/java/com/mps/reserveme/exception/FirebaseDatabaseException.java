package com.mps.reserveme.exception;

public class FirebaseDatabaseException extends RuntimeException{
    public FirebaseDatabaseException(String message){
        super(message);
    }

    public FirebaseDatabaseException(){

    }
}

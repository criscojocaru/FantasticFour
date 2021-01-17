package com.mps.reserveme.service;

public enum ServiceMessages {

    REGISTER_USER_SUCCESS("Successfully registered user with id = %s at %s"),
    USER_NOT_FOUND("User with id = %s does not exist!"),
    UPDATE_USER_SUCCESS("Successfully updated user with id = %s"),
    GET_USER_SUCCESS("Successful GET user with id = %s");


    private String value;

    ServiceMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

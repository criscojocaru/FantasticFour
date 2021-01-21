package com.mps.reserveme.service;

public enum ServiceMessages {

    REGISTER_USER_SUCCESS("Successfully registered user with id = %s at %s"),
    USER_NOT_FOUND("User with id = %s does not exist!"),
    UPDATE_USER_SUCCESS("Successfully updated user with id = %s"),
    GET_USER_SUCCESS("Successful GET user with id = %s"),
    RESOURCE_NOT_FOUND("Could not find resource with id = %s"),
    RESOURCE_NOT_CREATED("Could not create resource"),
    CREATE_RESOURCE_SUCCESS("Resource with id = %s was successfully created"),
    UPDATE_RESOURCE_SUCCESS("Successfully updated resource with id = %s"),
    RESERVATION_NOT_FOUND("Could not find reservation with id = %s"),
    RESERVATION_NOT_CREATED("Could not create reservation"),
    CREATE_RESERVATION_SUCCESS("Successfully created reservation with id = %s"),
    UPDATE_RESERVATION_SUCCESS("Successfully updated reservation with id = %s");

    private String value;

    ServiceMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

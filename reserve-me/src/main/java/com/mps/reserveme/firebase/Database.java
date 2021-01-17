package com.mps.reserveme.firebase;

public enum Database {
    FIREBASE_URL("https://reserve-me-783b7.firebaseio.com"),
    USERS("users");

    private String value;

    Database(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

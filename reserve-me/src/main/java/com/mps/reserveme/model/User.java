package com.mps.reserveme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String role;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> subscribed;
}

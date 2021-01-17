package com.mps.reserveme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String kind;
    private String idToken;
    private String refreshToken;
    private String expiresIn;
    private Boolean isNewUser;
}

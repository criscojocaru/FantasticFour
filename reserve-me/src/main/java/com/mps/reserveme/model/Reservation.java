package com.mps.reserveme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private String userId;
    private String resourceId;
    private Timestamp start;
    private Timestamp end;
    private Boolean finished;
}

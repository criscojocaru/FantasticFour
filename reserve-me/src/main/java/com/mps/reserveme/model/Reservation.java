package com.mps.reserveme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private String reservationId;
    private String userId;
    private User user;
    private String resourceId;
    private String reason;
    private String start;
    private String end;
    private Boolean finished;
}

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
public class Resource {
    private String resourceId;
    private String description;
    private String name;
    private String state;
    private Integer capacity;
    private List<Reservation> reservations;
    private List<String> reservationIds;
}

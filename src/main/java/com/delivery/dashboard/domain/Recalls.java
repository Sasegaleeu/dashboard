package com.delivery.dashboard.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recalls {
    private int recall_id;
    private int delivery_id;
    private boolean is_recalled;
    private Timestamp recall_date;
    private Timestamp postponed_delivery_date;
}

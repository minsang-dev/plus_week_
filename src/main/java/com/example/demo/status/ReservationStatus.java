package com.example.demo.status;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    PENDING,
    APPROVED,
    CANCELLED,
    EXPIRED
}

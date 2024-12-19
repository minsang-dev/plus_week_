package com.example.demo.dto;

import com.example.demo.status.ReservationStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationRequestDto {
    private final Long itemId;
    private final Long userId;
    private final ReservationStatus status;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public ReservationRequestDto(Long itemId, Long userId, ReservationStatus status, LocalDateTime startAt, LocalDateTime endAt) {
        this.itemId = itemId;
        this.userId = userId;
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
    }


}

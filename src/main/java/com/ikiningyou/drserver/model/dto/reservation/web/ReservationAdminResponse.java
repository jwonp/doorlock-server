package com.ikiningyou.drserver.model.dto.reservation.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReservationAdminResponse {
    private Long reservationId;
    private String name;
    private String userId;
    private String phone;
    private String cardId;
    private String address;
}

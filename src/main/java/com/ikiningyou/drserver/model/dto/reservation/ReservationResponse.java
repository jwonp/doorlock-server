package com.ikiningyou.drserver.model.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReservationResponse {
    private Long id;
    private String userId;
    private int roomId;
    private String cardId;
}

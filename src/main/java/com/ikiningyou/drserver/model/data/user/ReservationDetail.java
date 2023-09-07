package com.ikiningyou.drserver.model.data.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReservationDetail {
    private Long reservationId;
    private String cardId;
    private String address;
    
}

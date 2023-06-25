package com.ikiningyou.drserver.model.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReservationWithUserResponse  {
    private Long id;
    private String cardId;
    private int roomId;
    private String userId;
    private String name;
    private String phone;
    
}

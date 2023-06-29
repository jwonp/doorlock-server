package com.ikiningyou.drserver.model.data.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReservationWithoutRoomId {
    
  private Long id;
  private String userId;
  private String cardId;
}

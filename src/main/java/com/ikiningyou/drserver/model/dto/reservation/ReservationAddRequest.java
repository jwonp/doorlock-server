package com.ikiningyou.drserver.model.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationAddRequest {

  private String userId;
  private int roomId;
  private String cardId;
}

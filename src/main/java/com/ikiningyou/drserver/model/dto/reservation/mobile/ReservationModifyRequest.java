package com.ikiningyou.drserver.model.dto.reservation.mobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationModifyRequest {

  private String userId;
  private Integer roomId;
  private String cardId;
}

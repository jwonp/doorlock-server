package com.ikiningyou.drserver.model.data.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReservationWithoutUserId {

  private Long id;
  private int roomId;
  private String cardId;
}

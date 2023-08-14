package com.ikiningyou.drserver.model.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationCheckInRequest {

  private Long reservationId;
  private boolean checkIn;
}

package com.ikiningyou.drserver.model.dto.reservation.web;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReservationWithProfile {

  private Long reservationId;
  private String name;
  private String phone;
  private String address;
  private Timestamp createdTime;
}

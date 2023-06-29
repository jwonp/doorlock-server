package com.ikiningyou.drserver.model.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReservationFullResponse {

  private Long id;

  private String userId;
  private String name;
  private String phone;

  private String cardId;

  private int roomId;
  private String address;

  private Boolean isCheckedIn;
}

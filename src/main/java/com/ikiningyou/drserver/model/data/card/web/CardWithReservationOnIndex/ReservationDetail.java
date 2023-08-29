package com.ikiningyou.drserver.model.data.card.web.CardWithReservationOnIndex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReservationDetail {

  private Long reservationId;
  private String name;
  private String userId;
  private String phone;
  private String address;
}

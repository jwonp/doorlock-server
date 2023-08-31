package com.ikiningyou.drserver.model.dto.card.web;

import com.ikiningyou.drserver.model.data.card.web.CardWithReservationOnIndex.ReservationDetail;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CardIndexResponse {

  private String cardId;
  private ReservationDetail reservation;
  private LocalDateTime lastTagged;
  private Timestamp lostTime;
}

package com.ikiningyou.drserver.model.dto.card.mobile;

import com.ikiningyou.drserver.model.data.TechType;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CardWithReservationResponse {

  private String id;
  private int maxSize;
  private String type;
  private TechType techType;
  private LocalDateTime lastTagged;
  private Long reservationId;
  private String userId;
  private int roomId;
}

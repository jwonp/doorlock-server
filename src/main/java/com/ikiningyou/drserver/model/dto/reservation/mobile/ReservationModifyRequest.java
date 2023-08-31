package com.ikiningyou.drserver.model.dto.reservation.mobile;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationModifyRequest {

  private Optional<String> userId;
  private Optional<Integer> roomId;
  private Optional<String> cardId;
}

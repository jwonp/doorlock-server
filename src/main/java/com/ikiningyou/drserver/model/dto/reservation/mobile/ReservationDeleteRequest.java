package com.ikiningyou.drserver.model.dto.reservation.mobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationDeleteRequest {

  private Long[] idList;
}

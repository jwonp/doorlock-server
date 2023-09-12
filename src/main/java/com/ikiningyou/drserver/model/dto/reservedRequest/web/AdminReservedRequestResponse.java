package com.ikiningyou.drserver.model.dto.reservedRequest.web;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AdminReservedRequestResponse {

  private Long requestId;
  private Long reservationId;
  private String name;
  private String userId;
  private String phone;
  private int roomId;
  private String address;
  private Timestamp requestedTime;
}

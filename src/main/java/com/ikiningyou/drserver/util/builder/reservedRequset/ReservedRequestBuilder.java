package com.ikiningyou.drserver.util.builder.reservedRequset;

import com.ikiningyou.drserver.model.dao.ReservedRequest;
import com.ikiningyou.drserver.model.dto.reservedRequest.web.AdminReservedRequestResponse;

public class ReservedRequestBuilder {

  public static AdminReservedRequestResponse ReservedRequestToAdminReservedRequestResponse(
    ReservedRequest request
  ) {
    return AdminReservedRequestResponse
      .builder()
      .requestId(request.getId())
      .reservationId(request.getReservation().getId())
      .name(request.getUser().getName())
      .userId(request.getUser().getId())
      .phone(request.getUser().getPhone())
      .roomId(request.getRoom().getId())
      .address(request.getRoom().getAddress())
      .requestedTime(request.getRequestedTime())
      .build();
  }
}

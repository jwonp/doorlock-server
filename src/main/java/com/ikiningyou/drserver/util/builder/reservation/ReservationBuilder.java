package com.ikiningyou.drserver.util.builder.reservation;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.dto.reservation.ReservationFullResponse;
import com.ikiningyou.drserver.model.dto.reservation.ReservationResponse;
import com.ikiningyou.drserver.model.dto.reservation.ReservationWithUserResponse;

public class ReservationBuilder {

  public static ReservationResponse ReservationToReservationResponse(
    Reservation reservation
  ) {
    return ReservationResponse
      .builder()
      .id(reservation.getId())
      .userId(reservation.getUser().getId())
      .cardId(reservation.getCard().getId())
      .roomId(reservation.getRoom().getId())
      .isCheckedIn(reservation.getIsCheckedIn())
      .build();
  }

  public static ReservationWithUserResponse ReservationToReservationWithUserResponse(
    Reservation reservation
  ) {
    return ReservationWithUserResponse
      .builder()
      .id(reservation.getId())
      .cardId(reservation.getCard().getId())
      .roomId(reservation.getRoom().getId())
      .userId(reservation.getUser().getId())
      .name(reservation.getUser().getName())
      .phone(reservation.getUser().getPhone())
      .build();
  }

  public static ReservationFullResponse ReservationToReservationFullResponse(
    Reservation reservation
  ) {
    return ReservationFullResponse
      .builder()
      .id(reservation.getId())
      .userId(reservation.getUser().getId())
      .name(reservation.getUser().getName())
      .phone(reservation.getUser().getPhone())
      .cardId(reservation.getCard().getId())
      .roomId(reservation.getRoom().getId())
      .address(reservation.getRoom().getAddress())
      .isCheckedIn(reservation.getIsCheckedIn())
      .build();
  }
}

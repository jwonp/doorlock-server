package com.ikiningyou.drserver.util.builder.reservation;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationFullResponse;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationResponse;
import com.ikiningyou.drserver.model.dto.reservation.mobile.ReservationWithUserResponse;
import com.ikiningyou.drserver.model.dto.reservation.web.ReservationAdminResponse;

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

  public static ReservationAdminResponse ReservationToReservationAdminResponse(
    Reservation reservation
  ) {
    return ReservationAdminResponse
      .builder()
      .reservationId(reservation.getId())
      .name(reservation.getUser().getName())
      .userId(reservation.getUser().getId())
      .phone(reservation.getUser().getPhone())
      .cardId(reservation.getCard().getId())
      .address(reservation.getRoom().getAddress())
      .build();
  }
}

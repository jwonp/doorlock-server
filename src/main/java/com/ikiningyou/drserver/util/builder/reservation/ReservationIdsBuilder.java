package com.ikiningyou.drserver.util.builder.reservation;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.data.reservation.ReservationWithoutRoomId;
import com.ikiningyou.drserver.model.data.reservation.ReservationWithoutUserId;
import java.util.ArrayList;
import java.util.List;

public class ReservationIdsBuilder {

  public static ReservationWithoutUserId[] ReservationsToReservationWithoutUserIdArray(
    List<Reservation> reservations
  ) {
    List<ReservationWithoutUserId> reservationList = new ArrayList<ReservationWithoutUserId>();
    for (Reservation reservation : reservations) {
      reservationList.add(ReservationToReservationWithoutUserId(reservation));
    }
    return reservationList.toArray(
      new ReservationWithoutUserId[reservationList.size()]
    );
  }

  public static ReservationWithoutUserId ReservationToReservationWithoutUserId(
    Reservation reservation
  ) {
    return ReservationWithoutUserId
      .builder()
      .id(reservation.getId())
      .roomId(reservation.getRoom().getId())
      .cardId(reservation.getCard().getId())
      .build();
  }

  public static ReservationWithoutRoomId[] ReservationsToReservationWithoutRoomIdArray(
    List<Reservation> reservations
  ) {
    List<ReservationWithoutRoomId> reservationList = new ArrayList<ReservationWithoutRoomId>();
    for (Reservation reservation : reservations) {
      reservationList.add(ReservationToReservationWithoutRoomId(reservation));
    }
    return reservationList.toArray(
      new ReservationWithoutRoomId[reservationList.size()]
    );
  }

  public static ReservationWithoutRoomId ReservationToReservationWithoutRoomId(
    Reservation reservation
  ) {
    return ReservationWithoutRoomId
      .builder()
      .id(reservation.getId())
      .userId(reservation.getUser().getId())
      .cardId(reservation.getCard().getId())
      .build();
  }
}

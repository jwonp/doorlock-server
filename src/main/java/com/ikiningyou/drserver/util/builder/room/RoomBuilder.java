package com.ikiningyou.drserver.util.builder.room;

import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.data.room.ReservationDetail;
import com.ikiningyou.drserver.model.dto.room.mobile.RoomResponse;
import com.ikiningyou.drserver.model.dto.room.mobile.RoomWithReservation;
import com.ikiningyou.drserver.model.dto.room.web.RoomAdminResponse;
import com.ikiningyou.drserver.util.builder.reservation.ReservationIdsBuilder;

public class RoomBuilder {

  public static RoomResponse RoomToRoomResponse(Room room) {
    return RoomResponse
      .builder()
      .id(room.getId())
      .address(room.getAddress())
      .build();
  }

  public static RoomWithReservation RoomToRoomWithReservation(Room room) {
    return RoomWithReservation
      .builder()
      .id(room.getId())
      .address(room.getAddress())
      .reservations(
        ReservationIdsBuilder.ReservationsToReservationWithoutRoomIdArray(
          room.getReservations()
        )
      )
      .build();
  }

  public static RoomAdminResponse RoomToRoomAdminResponse(Room room) {
    return RoomAdminResponse
      .builder()
      .roomId(room.getId())
      .address(room.getAddress())
      .reservations(
        room
          .getReservations()
          .stream()
          .map(item ->
            ReservationDetail
              .builder()
              .reservationId(item.getId())
              .name(item.getUser().getName())
              .userId(item.getUser().getId())
              .phone(item.getUser().getPhone())
              .cardId(item.getCard().getId())
              .build()
          )
          .toArray(ReservationDetail[]::new)
      )
      .build();
  }
}

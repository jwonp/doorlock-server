package com.ikiningyou.drserver.util.builder.room;

import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dto.room.RoomResponse;
import com.ikiningyou.drserver.model.dto.room.RoomWithReservation;
import com.ikiningyou.drserver.util.builder.reservation.ReservationBuilder;
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
}

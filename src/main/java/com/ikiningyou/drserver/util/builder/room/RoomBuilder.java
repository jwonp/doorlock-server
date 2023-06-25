package com.ikiningyou.drserver.util.builder.room;

import com.ikiningyou.drserver.model.dao.Room;
import com.ikiningyou.drserver.model.dto.room.RoomResponse;

public class RoomBuilder {

  public static RoomResponse RoomToRoomResponse(Room room) {
    return RoomResponse
      .builder()
      .id(room.getId())
      .address(room.getAddress())
      .build();
  }
}

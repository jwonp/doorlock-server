package com.ikiningyou.drserver.model.dto.room;

import com.ikiningyou.drserver.model.data.reservation.ReservationWithoutRoomId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RoomWithReservation {
    private int roomId;
    private String address;
    private ReservationWithoutRoomId[] reservations;
}

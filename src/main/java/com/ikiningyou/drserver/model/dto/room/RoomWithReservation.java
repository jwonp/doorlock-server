package com.ikiningyou.drserver.model.dto.room.mobile;

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
    private int id;
    private String address;
    private ReservationWithoutRoomId[] reservations;
}

package com.ikiningyou.drserver.model.dto.room.web;

import com.ikiningyou.drserver.model.data.room.ReservationDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RoomAdminResponse {
    private int roomId;
    private String address;
    private ReservationDetail[] reservations;
}

package com.ikiningyou.drserver.model.dto.room.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RoomSelectResponse {
    private int roomId;
    private String address;
}

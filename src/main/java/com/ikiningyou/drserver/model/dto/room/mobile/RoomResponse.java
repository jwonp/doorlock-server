package com.ikiningyou.drserver.model.dto.room.mobile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RoomResponse {

  private int id;
  private String address;
}

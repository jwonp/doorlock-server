package com.ikiningyou.drserver.model.dto.room.mobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoomDeleteRequest {

  private Integer[] idList;
}

package com.ikiningyou.drserver.model.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoomDeleteRequest {

  private Integer[] idList;
}

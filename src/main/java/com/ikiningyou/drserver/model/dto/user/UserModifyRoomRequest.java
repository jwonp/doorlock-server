package com.ikiningyou.drserver.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserModifyRoomRequest {

  private int roomId;
  private String userId;
}

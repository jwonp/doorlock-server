package com.ikiningyou.drserver.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserModifyCardRequest {

  private String userId;
  private String cardId;
}

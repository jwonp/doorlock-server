package com.ikiningyou.drserver.model.dto.user;

import com.ikiningyou.drserver.model.dao.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserListWithReservationResponse extends User {

  private int roomId;
  private String cardId;
}

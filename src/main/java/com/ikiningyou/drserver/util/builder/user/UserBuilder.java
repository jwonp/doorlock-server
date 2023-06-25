package com.ikiningyou.drserver.util.builder.user;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.user.UserResponse;

public class UserBuilder {

  public static UserResponse UserToUserResponse(User user) {
    return UserResponse
      .builder()
      .id(user.getId())
      .name(user.getName())
      .phone(user.getPhone())
      .lastTagged(user.getLastTagged())
      .build();
  }
}

package com.ikiningyou.drserver.util.builder.user;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.dto.user.UserResponse;
import com.ikiningyou.drserver.model.dto.user.UserWithReservationsResponse;
import com.ikiningyou.drserver.util.builder.reservation.ReservationIdsBuilder;

public class UserBuilder {

  public static UserResponse UserToUserResponse(User user) {
    return UserResponse
      .builder()
      .id(user.getId())
      .name(user.getName())
      .phone(user.getPhone())
      .build();
  }

  public static UserWithReservationsResponse UserToUserWithReservationsResponse(
    User user
  ) {
    return UserWithReservationsResponse
      .builder()
      .id(user.getId())
      .name(user.getName())
      .phone(user.getPhone())
      .reservations(
        ReservationIdsBuilder.ReservationsToReservationWithoutUserIdArray(
          user.getReservations()
        )
      )
      .build();
  }
}

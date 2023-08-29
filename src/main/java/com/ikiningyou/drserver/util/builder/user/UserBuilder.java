package com.ikiningyou.drserver.util.builder.user;

import com.ikiningyou.drserver.model.dao.User;
import com.ikiningyou.drserver.model.data.user.ReservationDetail;
import com.ikiningyou.drserver.model.dto.user.mobile.UserResponse;
import com.ikiningyou.drserver.model.dto.user.mobile.UserWithReservationsResponse;
import com.ikiningyou.drserver.model.dto.user.web.UserAdminResponse;
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

  public static UserAdminResponse UserToUserAdminResponse(User user) {
    return UserAdminResponse
      .builder()
      .userId(user.getId())
      .name(user.getName())
      .phone(user.getPhone())
      .reservations(
        user
          .getReservations()
          .stream()
          .map(item ->
            ReservationDetail
              .builder()
              .reservationId(item.getId())
              .cardId(item.getCard().getId())
              .address(item.getRoom().getAddress())
              .build()
          )
          .toArray(ReservationDetail[]::new)
      )
      .build();
  }
}

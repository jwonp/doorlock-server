package com.ikiningyou.drserver.model.dto.user;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.data.reservation.ReservationWithoutUserId;
import com.ikiningyou.drserver.model.dto.reservation.ReservationResponse;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserWithReservationsResponse {

  private String id;
  private String name;
  private String phone;

  private ReservationWithoutUserId[] reservations;
}

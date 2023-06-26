package com.ikiningyou.drserver.model.dto.user;

import java.util.Date;

import com.ikiningyou.drserver.model.dao.Reservation;
import com.ikiningyou.drserver.model.data.reservation.ReservationWithoutUserId;
import com.ikiningyou.drserver.model.dto.reservation.ReservationResponse;

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
  private Date lastTagged;
  private ReservationWithoutUserId[] reservations;

}

package com.ikiningyou.drserver.model.dto.user.web;

import com.ikiningyou.drserver.model.data.user.ReservationDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserAdminResponse {
    private String userId;
    private String name;
    private String phone;
    private ReservationDetail[] reservations;
}

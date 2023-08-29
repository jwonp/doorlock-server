package com.ikiningyou.drserver.model.dto.card.web;

import java.time.LocalDateTime;

import com.ikiningyou.drserver.model.data.card.web.CardWithReservationOnIndex.ReservationDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CardWithReservationOnIndexResponse {
    private String cardId;
    private ReservationDetail reservation;
    private LocalDateTime lastTagged;
}

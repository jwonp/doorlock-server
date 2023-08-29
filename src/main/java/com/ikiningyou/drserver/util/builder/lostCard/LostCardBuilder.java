package com.ikiningyou.drserver.util.builder.lostCard;

import com.ikiningyou.drserver.model.dao.LostCard;
import com.ikiningyou.drserver.model.dto.lostCard.web.LostCardAdminResponse;
import com.ikiningyou.drserver.model.dto.lostCard.web.LostCardListResponse;
import org.springframework.security.access.method.P;

public class LostCardBuilder {

  public static LostCardListResponse LostCardToLostCardListResponse(
    LostCard lostCard
  ) {
    if (lostCard.getCard().getReservation() == null) {
      return LostCardListResponse
        .builder()
        .cardId(lostCard.getCard().getId())
        .lastTagged(lostCard.getCard().getLastTagged())
        .build();
    }
    return LostCardListResponse
      .builder()
      .cardId(lostCard.getCard().getId())
      .name(lostCard.getCard().getReservation().getUser().getName())
      .userId(lostCard.getCard().getReservation().getUser().getId())
      .address(lostCard.getCard().getReservation().getRoom().getAddress())
      .lastTagged(lostCard.getCard().getLastTagged())
      .build();
  }

  public static LostCardAdminResponse LostCardToLostCardAdminResponse(
    LostCard card
  ) {
    if (card.getCard().getReservation() == null) {
      return LostCardAdminResponse
        .builder()
        .cardId(card.getCard().getId())
        .lastTagged(card.getCard().getLastTagged())
        .build();
    }

    return LostCardAdminResponse
      .builder()
      .cardId(card.getCard().getId())
      .userId(card.getCard().getReservation().getUser().getId())
      .name(card.getCard().getReservation().getUser().getName())
      .phone(card.getCard().getReservation().getUser().getPhone())
      .address(card.getCard().getReservation().getRoom().getAddress())
      .lastTagged(card.getCard().getLastTagged())
      .build();
  }
}

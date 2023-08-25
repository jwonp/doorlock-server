package com.ikiningyou.drserver.util.builder.lostCard;

import com.ikiningyou.drserver.model.dao.LostCard;
import com.ikiningyou.drserver.model.dto.lostCard.LostCardListResponse;

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
}

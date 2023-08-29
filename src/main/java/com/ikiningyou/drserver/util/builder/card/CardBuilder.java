package com.ikiningyou.drserver.util.builder.card;

import com.ikiningyou.drserver.model.dao.Card;
import com.ikiningyou.drserver.model.data.card.web.CardWithReservationOnIndex.CardWithReservationOnIndex;
import com.ikiningyou.drserver.model.data.card.web.CardWithReservationOnIndex.ReservationDetail;
import com.ikiningyou.drserver.model.dto.card.mobile.CardAddRequest;
import com.ikiningyou.drserver.model.dto.card.mobile.CardResponse;
import com.ikiningyou.drserver.model.dto.card.mobile.CardWithReservationResponse;
import com.ikiningyou.drserver.model.dto.card.web.CardAdminDetailResponse;
import com.ikiningyou.drserver.model.dto.card.web.CardWithReservationOnIndexResponse;
import com.ikiningyou.drserver.util.NfcCardTechTypeParser;

public class CardBuilder {

  public static CardResponse CardToCardResponse(Card card) {
    return CardResponse
      .builder()
      .id(card.getId())
      .maxSize(card.getMaxSize())
      .type(card.getType())
      .lastTagged(card.getLastTagged())
      .techType(TechTypeBuilder.CardtoTechType(card))
      .build();
  }

  public static CardWithReservationResponse CardToCardWithReservationResponse(
    Card card
  ) {
    return CardWithReservationResponse
      .builder()
      .id(card.getId())
      .maxSize(card.getMaxSize())
      .type(card.getType())
      .lastTagged(card.getLastTagged())
      .techType(TechTypeBuilder.CardtoTechType(card))
      .reservationId(
        card.getReservation() == null ? 0l : card.getReservation().getId()
      )
      .roomId(
        card.getReservation() == null
          ? 0
          : card.getReservation().getRoom().getId()
      )
      .userId(
        card.getReservation() == null
          ? null
          : card.getReservation().getUser().getId()
      )
      .build();
  }

  public static Card CardAddRequestToCard(CardAddRequest newCard) {
    String[] techTypes = newCard.getTechTypes();
    boolean[] techTypeFlags = NfcCardTechTypeParser.parseTechType(techTypes);
    return Card
      .builder()
      .id(newCard.getId())
      .maxSize(newCard.getMaxSize())
      .type(newCard.getType())
      .isIsoDep(techTypeFlags[NfcCardTechTypeParser.IS_ISO_DEP])
      .isNfcA(techTypeFlags[NfcCardTechTypeParser.IS_NFC_A])
      .isNfcB(techTypeFlags[NfcCardTechTypeParser.IS_NFC_B])
      .isNfcF(techTypeFlags[NfcCardTechTypeParser.IS_NFC_F])
      .isNfcV(techTypeFlags[NfcCardTechTypeParser.IS_NFC_V])
      .isNdef(techTypeFlags[NfcCardTechTypeParser.IS_NDEF])
      .isNdefFormatable(techTypeFlags[NfcCardTechTypeParser.IS_NDEF_FORMATABLE])
      .isMifareClassic(techTypeFlags[NfcCardTechTypeParser.IS_MIFARE_CLASSIC])
      .isMifareUltralight(
        techTypeFlags[NfcCardTechTypeParser.IS_MIFARE_ULTRA_LIGHT]
      )
      .build();
  }

  public static CardWithReservationOnIndexResponse CardWithReservationOnIndexToCardWithReservationOnIndexResponse(
    CardWithReservationOnIndex card
  ) {
    return CardWithReservationOnIndexResponse
      .builder()
      .cardId(card.getCardId())
      .reservation(
        ReservationDetail
          .builder()
          .reservationId(card.getreservationId())
          .name(card.getName())
          .userId(card.getUserId())
          .phone(card.getPhone())
          .address(card.getAddress())
          .build()
      )
      .lastTagged(card.getLastTagged())
      .build();
  }

  public static CardAdminDetailResponse CardToCardAdminDetailResponse(Card card) {
    if (card.getReservation() == null) {
      return CardAdminDetailResponse
        .builder()
        .cardId(card.getId())
        .lastTagged(card.getLastTagged())
        .build();
    }
    return CardAdminDetailResponse
      .builder()
      .cardId(card.getId())
      .reservation(
        ReservationDetail
          .builder()
          .reservationId(card.getReservation().getId())
          .name(card.getReservation().getUser().getName())
          .userId(card.getReservation().getUser().getId())
          .phone(card.getReservation().getUser().getPhone())
          .address(card.getReservation().getRoom().getAddress())
          .build()
      )
      .lastTagged(card.getLastTagged())
      .build();
  }
}
